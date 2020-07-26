package com.example.submissiondua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissiondua.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_USER = "ID_USER"
    }
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val username = intent.getStringExtra(ID_USER)
        supportActionBar?.title = username
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        detailViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(
            DetailViewModel::class.java)

        username?.let { detailViewModel.setUser(it) }

        detailViewModel.getUser().observe(this, Observer {user ->
            if(user!=null){
                count_repo.text = user.countRepo.toString()
                count_followers.text = user.countFollowers.toString()
                count_following.text = user.countFollowing.toString()
                name.text = user.name
                company.text = user.company
                location.text = user.location
                bio.text = user.bio
                Glide.with(this)
                    .load(user.avatar)
                    .apply(RequestOptions().override(75, 75))
                    .into(img_user)
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}