package com.example.todolist

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.*
import com.example.todolist.databinding.ActivityMainBinding
import io.ktor.client.statement.*
import io.ktor.http.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() , NewTaskDialogFragment.NewTaskDialogListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var todoListItems = ArrayList<String>()
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        binding.fab.setOnClickListener { /*view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/showNewTaskUI()
        }
        listView = findViewById(R.id.list_view)
        populateListView()

        /*val htmlContent = httpClient.request<String> {
            val  url("https://a962-105-161-91-32.ngrok.io/")
            method = HttpMethod.Get
        }
        val response = httpClient.post<HttpResponse>("https://a962-105-161-91-32.ngrok.io/") {
            headers {
                append("Authorization", "token")
            }
            body = "Command"
        }*/
    }
    object NetworkInfo {
        private const val URL ="https://disease.sh/v2/"
        //CREATE HTTP CLIENT
        private val okHttp = OkHttpClient.Builder()

        //retrofit builder
        private val builder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

        //create retrofit Instance
        private val retrofit = builder.build()

        //we will use this class to create an anonymous inner class function that
        //implements Country service Interface


        fun <T> buildService (serviceType :Class<T>):T{
            return retrofit.create(serviceType)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

   /* override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialog_title)

        newFragment.show(supportFragmentManager, "newtask")
        //newFragment.show(fragmentManager, "newtask")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        todoListItems.add(task)
        listAdapter?.notifyDataSetChanged()

        Snackbar.make(binding.fab, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("Not yet implemented")
    }
    private fun populateListView() {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoListItems)
        listView?.adapter = listAdapter
    }

}