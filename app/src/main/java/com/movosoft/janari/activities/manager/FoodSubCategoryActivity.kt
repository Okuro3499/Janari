package com.movosoft.janari.activities.manager

import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.R
import com.movosoft.janari.activities.manager.adapters.AdapterSubCategory
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.databinding.ActivityFoodSubCategoryBinding
import com.movosoft.janari.models.GetCategory_SubCategory
import com.movosoft.janari.models.categoryModel.Categories
import com.movosoft.janari.models.categoryModel.GetCategoryResponse
import com.movosoft.janari.models.subCategoryModel.GetSubCategoryResponse
import com.movosoft.janari.models.subCategoryModel.SubCategorySetup
import com.movosoft.janari.models.subCategoryModel.SubCategorySetupResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodSubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityFoodSubCategoryBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var apiClient: ApiClient
    var categoryId: String? = null

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

            val bNameArr: String? = sharedPreferences.getString("CATEGORY_ARR", "")
            val tempStr = bNameArr!!.replace("[", "").replace("]", "")
            val bNameT: Array<String?> = tempStr.split(",").toTypedArray()
            val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(this@FoodSubCategoryActivity, android.R.layout.simple_dropdown_item_1line, bNameT)
            category.adapter = adapter

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

            submitBtn.setOnClickListener { it ->
                val ct: String = category.selectedItem.toString().trim { it <= ' ' }
                val c = ct.split(":").toTypedArray()
                categoryId = c[0]

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
                        categoryId!!.toInt(),
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

        getSubCategories()
        getCategories()

    }

    private fun getSubCategories() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

//        val progressDialog = ProgressDialog(this@FooddSubCategoryActivity)
//        progressDialog.setCancelable(false) // set cancelable to false
//        progressDialog.setMessage("Fetching Categories...") // set message
//        progressDialog.show()

//        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        binding.shimmerLayout.startShimmer();
        val getSubCategoryInfo = GetCategory_SubCategory(
            sharedPreferences.getString("companyID", "")?.toInt(),
        )
        apiClient.getApiService(this).getFoodSubCategories(getSubCategoryInfo).enqueue(object : Callback<GetSubCategoryResponse> {
            override fun onResponse(call: Call<GetSubCategoryResponse>, response: Response<GetSubCategoryResponse>) {
                if (response.isSuccessful) {
//                    progressDialog.dismiss()
                    binding.recyclerView.apply {
                        binding.etSearch.visibility = View.VISIBLE
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        //use this in Gari
                        val linearLayoutManager = LinearLayoutManager(this@FoodSubCategoryActivity)
                        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//                        {or VERTICAL}
                        binding.recyclerView.layoutManager = linearLayoutManager
                        //upto here
                        adapter = response.body()?.let { AdapterSubCategory(it.data, context) }
                        Log.d("data", response.body()?.data.toString())
                    }
                }
            }

            override fun onFailure(call: Call<GetSubCategoryResponse>, t: Throwable) {
                Log.e("Gideon", "onFailure: ${t.message}")
            }
        })
    }

    fun getCategories() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

        val getCategoryInfo = GetCategory_SubCategory(
            sharedPreferences.getString("companyID", "")?.toInt(),
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        apiClient.getApiService(this).getFoodCategories(getCategoryInfo).enqueue(object : Callback<GetCategoryResponse> {
            override fun onResponse(call: Call<GetCategoryResponse>, response: Response<GetCategoryResponse>) {
                if (response.isSuccessful) {
                    Log.d("data", response.body().toString())
//                    val cateNamesArr = ArrayList<String>()
//                    val jsonArray: List<Categories>? = response.body().data
//                    if (jsonArray.toString().isNotEmpty()) {
//                        if (jsonArray != null) {
//                            for (i in jsonArray.indices) {
//                                val jsonObjectI = jsonArray[i]
//
//                                val tempCategory = response.body().data.Categories.categoryID + ":" + response.body()?.data.description                                    )
//                                cateNamesArr.add(tempCategory)
//                            }
//                        }
//                        editor.putString("CATEGORY_ARR", cateNamesArr.toString())


//                    }

                }
            }

            override fun onFailure(call: Call<GetCategoryResponse>, t: Throwable) {
                Log.e("Gideon", "onFailure: ${t.message}")
            }
        })
    }

    private fun restartApp() {
        recreate()
    }
}