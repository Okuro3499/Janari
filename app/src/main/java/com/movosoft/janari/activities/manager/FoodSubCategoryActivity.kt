package com.movosoft.janari.activities.manager

import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.R
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.databinding.ActivityFoodSubCategoryBinding
import com.movosoft.janari.models.subCategoryModel.SubCategorySetup
import com.movosoft.janari.models.subCategoryModel.SubCategorySetupResponse
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodSubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityFoodSubCategoryBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var apiClient: ApiClient
    var categoryList: ArrayList<String> = ArrayList()
    var categoryAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodSubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

        binding.bottomNav.selectedItemId = R.id.menu

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu ->{
                    startActivity(Intent(applicationContext, ManagerMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(applicationContext, ManagerReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sales -> {
                    startActivity(Intent(applicationContext, ManagerSalesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected: " + item.itemId)
            //TODO: set visibility
            when (item.itemId) {
                R.id.branches -> {
                    startActivity(Intent(this@FoodSubCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodCategory -> {
                    startActivity(
                        Intent(
                            this@FoodSubCategoryActivity,
                            FoodCategoryActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodSubCategory -> {
                    startActivity(
                        Intent(
                            this@FoodSubCategoryActivity,
                            FoodSubCategoryActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@FoodSubCategoryActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(
                        Intent(
                            this@FoodSubCategoryActivity,
                            ManagerReportsActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@FoodSubCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@FoodSubCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true

                }
                R.id.logout -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })

        binding.addNew.setOnClickListener {
            val dialog = Dialog(this@FoodSubCategoryActivity)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.new_sub_category_dialog)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val category = dialog.findViewById<Spinner>(R.id.spinCategory)
            val subCategoryName = dialog.findViewById(R.id.etSubCategory) as EditText
            val submitBtn = dialog.findViewById(R.id.submit) as AppCompatButton
            val noBtn = dialog.findViewById(R.id.cancel) as AppCompatButton

//            val url = ""
//            val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
//            val jsonObject = JSONObject()
//
//            val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, jsonObject, { response ->
//                try {
//                    val array = JSONArray(response.getString("result"))
//                    if (array.length() == 0) {
//                    } else {
//                        for (i in 0 until array.length()) {
//                            val `object`: JSONObject = array.getJSONObject(i)
//                            Log.d("GIDEONOBJ", `object`.getString("firstName"))
//
//                            val categoryName = `object`.optString("lastName")
//                            val categoryId = `object`.optString("id")
//                            categoryList.add("$categoryId: $categoryName")
//                            categoryAdapter = ArrayAdapter<String>(this@FoodSubCategoryActivity, android.R.layout.simple_spinner_item, categoryList)
//                            categoryAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                            category.adapter = categoryAdapter
//                            category.onItemSelectedListener = object :
//                                AdapterView.OnItemSelectedListener {
//                                override fun onItemSelected(
//                                    parent: AdapterView<*>?, view: View, position: Int, id: Long) {
//                                    val brT: String = category.selectedItem.toString().trim()
//                                    val br = brT.split(":").toTypedArray()
//                                    val item = br[0]
////                                    pref.setMEMBERID(item)
//                                }
//
//                                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
//                            }
//                        }
//                        Log.i(TAG, "")
//                    }
//                } catch (e: Exception) {
//                    Log.i(TAG, Log.getStackTraceString(e))
//                }
//            }, { error -> Log.e("error", error.toString()) }) {
//                val headers: Map<String, String> get(){
//                        val params: MutableMap<String, String> = HashMap()
//                        params["Content-Type"] = "application/json"
//                        params["Authorization"] = "Bearer $token"
//                        return params
//                    }
//                val bodyContentType: String
//                    get() = "application/json"
//            }
//            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//            queue.add(jsonObjectRequest)

            submitBtn.setOnClickListener {
                if(category.selectedItem.toString().trim() == "") {
                    Toast.makeText(this@FoodSubCategoryActivity, "Kindly choose category", Toast.LENGTH_SHORT).show();
                } else if  (TextUtils.isEmpty(subCategoryName.text.toString().trim())) {
                    subCategoryName.error = "Kindly enter a sub-category name!"
                } else {
                    val progressDialog = ProgressDialog(this@FoodSubCategoryActivity)
                    progressDialog.setCancelable(false) // set cancelable to false
                    progressDialog.setMessage("Creating sub-category...") // set message
                    progressDialog.show()


                    val subCategoryInfo = SubCategorySetup(
                        sharedPreferences.getString("companyID", "")?.toInt(),
                        sharedPreferences.getString("categoryID", "")?.toInt(),
                        subCategoryName.text.toString(),
                        1
                    )
                    apiClient.getApiService(this).createSubCategory(subCategoryInfo).enqueue(object : Callback<SubCategorySetupResponse> {
                        override fun onResponse(call: Call<SubCategorySetupResponse>, response: Response<SubCategorySetupResponse>) {
                            if (response.isSuccessful) {
                                progressDialog.dismiss()
                                dialog.dismiss()

                                val successDialog = Dialog(this@FoodSubCategoryActivity)
                                successDialog.setCancelable(false)
                                successDialog.setContentView(R.layout.success_dialog)
                                successDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                                val successText = successDialog.findViewById(R.id.successText) as TextView
                                val ok = successDialog.findViewById(R.id.submit) as AppCompatButton

                                successText.text = "Sub-Category created successfully"
                                ok.setOnClickListener { successDialog.dismiss() }

                                successDialog.show()
                                Log.e("Gideon", "onSuccess: ${response.body()}")

                            }
                        }

                        override fun onFailure(call: Call<SubCategorySetupResponse>, t: Throwable) {
                            progressDialog.dismiss()
                            Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                            Log.e("Gideon", "onFailure: ${t.message}")
                        }
                    })
                }
            }

            noBtn.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }
}