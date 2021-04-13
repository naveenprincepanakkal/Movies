package poc.naveen.movies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import poc.naveen.movies.MainApplication
import poc.naveen.movies.R
import poc.naveen.movies.databinding.MainActivityBinding
import poc.naveen.movies.databinding.MainFragmentBinding
import poc.naveen.movies.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        MainApplication.appComponent.inject(this)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.setNavigationIcon(R.drawable.back)
        supportActionBar?.title = "Romantic Movies"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}