package pe.edu.upc.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.pokedex.fragment.DiscoveryFragment
import pe.edu.upc.pokedex.fragment.FavoritesFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        if (savedInstanceState == null) {
            val fragment = DiscoveryFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment, fragment.javaClass.simpleName)
                .commit()
        }

    }

    private fun initViews() {

        bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_discovery -> {
                    val fragment = DiscoveryFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment, fragment.javaClass.simpleName)
                        .commit()
                    true
                }
                R.id.navigation_favorites -> {
                    val fragment = FavoritesFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment, fragment.javaClass.simpleName)
                        .commit()
                    true
                }
                else -> true
            }
        }

    }

}