package com.nguyenhaidang_dangnh2406.moviekotlin.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.nguyenhaidang_dangnh2406.moviekotlin.R
import com.nguyenhaidang_dangnh2406.moviekotlin.adapters.ViewPagerAdapter
import com.nguyenhaidang_dangnh2406.moviekotlin.fragments.AboutFragment
import com.nguyenhaidang_dangnh2406.moviekotlin.fragments.FavoriteFragment
import com.nguyenhaidang_dangnh2406.moviekotlin.fragments.MovieFragment
import com.nguyenhaidang_dangnh2406.moviekotlin.fragments.SettingFragment
import kotlinx.android.synthetic.main.activity_master.*

class MasterActivity : AppCompatActivity() ,ViewPager.OnPageChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)
        initView()
    }



    private fun initView(){
        supportActionBar?.title = "Movie"
        var viewPager = ViewPagerAdapter(supportFragmentManager)
        viewPager.addFragment(MovieFragment.newInstance(),"Movie")
        viewPager.addFragment(FavoriteFragment.newInstance(),"Favorite")
        viewPager.addFragment(AboutFragment.newInstance(),"About")
        viewPager.addFragment(SettingFragment.newInstance(),"Setting")

        master_view_pager.adapter = viewPager
        master_tab_layout.setupWithViewPager(master_view_pager)
        master_view_pager.addOnPageChangeListener(this)

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {


        when(position){
            0 ->{
                supportActionBar?.title = "Movie"
            }
            1 ->{
                supportActionBar?.title = "Favorite"
            }
            2 ->{
                supportActionBar?.title = "About"
            }
            3 ->{
                supportActionBar?.title = "Setting"
            }
        }
    }
}