package poc.naveen.movies.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import poc.naveen.movies.MainApplication
import poc.naveen.movies.R
import poc.naveen.movies.data.model.Content
import poc.naveen.movies.data.model.Movies
import poc.naveen.movies.databinding.MainFragmentBinding
import poc.naveen.movies.ui.adapter.MainAdapter
import poc.naveen.movies.utils.Status
import poc.naveen.movies.viewmodel.MainViewModel
import poc.naveen.movies.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private lateinit var mLayoutManager: GridLayoutManager
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private lateinit var adapterMain: MainAdapter

    private var pageNum: Int = 1

    private var contentList: ArrayList<Content> = arrayListOf()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        MainApplication.appComponent.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setScrollListener()
        initViewModel()
        getMovies()
    }

    private fun initView() {
        adapterMain = MainAdapter(contentList)

        val spanCount = if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 7

        mLayoutManager = GridLayoutManager(activity, spanCount)

        binding.rvList.apply {
            layoutManager = mLayoutManager
            adapter = adapterMain
            //addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setScrollListener() {
        var isLoading: Boolean = true
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) { //check for scroll down
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        isLoading = false
                        if (pageNum <= 3) {
                            getMovies()
                        }
                        isLoading = true
                    }
                }
            }
        }
        binding.rvList.addOnScrollListener(scrollListener)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun getMovies() {
        viewModel.fetchMovies(pageNum).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { responseData -> handleResult(responseData) }
                Status.LOADING -> {
                }//mainSwipeLayout.isRefreshing = true
                Status.ERROR -> it.message?.let { errMsg -> handleError(errMsg) }
            }
        })
    }

    private fun handleResult(movies: Movies) {
        pageNum++
        contentList = movies.page.contentItems.content
        adapterMain.updateContent(contentList)
        //adapterMain.notifyDataSetChanged()
    }

    private fun handleError(errMsg: String) {
        Toast.makeText(context, "ERROR : $errMsg", Toast.LENGTH_LONG).show()
    }

}