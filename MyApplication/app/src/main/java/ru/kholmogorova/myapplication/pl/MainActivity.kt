    package ru.kholmogorova.myapplication.pl

    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.viewpager2.widget.ViewPager2
    import com.google.android.material.tabs.TabLayout
    import com.google.android.material.tabs.TabLayoutMediator
    import ru.kholmogorova.myapplication.R

    class MainActivity : AppCompatActivity() {
        private lateinit var viewPager: ViewPager2
        private lateinit var tabLayout: TabLayout

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            tabLayout = findViewById(R.id.tab_layout)
            viewPager = findViewById(R.id.view_pager)

            val adapter = TabsPagerAdapter(this)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "AURA"
                    1 -> "HISTORY"
                    2 -> "FRIENDS"
                    else -> throw IllegalArgumentException("Invalid position $position")
                }
            }.attach()

        }
    }