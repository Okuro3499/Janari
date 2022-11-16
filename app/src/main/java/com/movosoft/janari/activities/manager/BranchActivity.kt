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
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.R
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.databinding.ActivityBranchBinding
import com.movosoft.janari.models.branchModel.BranchSetup
import com.movosoft.janari.models.branchModel.BranchSetupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BranchActivity : AppCompatActivity() {
    lateinit var binding: ActivityBranchBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        apiClient = ApiClient

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.add.setOnClickListener{
            val dialog = Dialog(this@BranchActivity)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.branch_dialog)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val branchName = dialog.findViewById(R.id.etBranchName) as EditText
            val branchAddress = dialog.findViewById(R.id.etBranchAddress) as EditText
            val submitBtn = dialog.findViewById(R.id.submit) as AppCompatButton
            val noBtn = dialog.findViewById(R.id.cancel) as AppCompatButton

            submitBtn.setOnClickListener {
                if(TextUtils.isEmpty(branchName.text.toString().trim())) {
                    branchName.error = "Kindly enter a branch name!"
                } else if (TextUtils.isEmpty(branchAddress.text.toString().trim())) {
                    branchAddress.error = "Kindly enter branch address"
                } else {
                    val progressDialog = ProgressDialog(this@BranchActivity)
                    progressDialog.setCancelable(false) // set cancelable to false
                    progressDialog.setMessage("Creating branch...") // set message
                    progressDialog.show()

//                  val editor: SharedPreferences.Editor = sharedPreferences.edit()

                    val branchInfo = BranchSetup(
                        branchName.text.toString().trim(),
                        sharedPreferences.getString("companyID", "")?.let { it1 -> Integer.valueOf(it1) },
                        branchAddress.text.toString().trim(),
                        "info@movosoft.co.ke",
                        1
                    )
                    apiClient.getApiService(this).createBranch(branchInfo).enqueue(object : Callback<BranchSetupResponse> {
                        override fun onResponse(call: Call<BranchSetupResponse>, response: Response<BranchSetupResponse>) {
                            if (response.isSuccessful) {
                                progressDialog.dismiss()
                                dialog.dismiss()

                                val successDialog = Dialog(this@BranchActivity)
                                successDialog.setCancelable(false)
                                successDialog.setContentView(R.layout.success_dialog)
                                successDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                                val successText = successDialog.findViewById(R.id.successText) as TextView
                                val submitBtn = successDialog.findViewById(R.id.submit) as AppCompatButton

                                successText.text = "Branch created successfully"
                                submitBtn.setOnClickListener {
                                    successDialog.dismiss()
                                }

                                successDialog.show()
                                Log.e("Gideon", "onSuccess: ${response.body()}")

                            }
                        }

                        override fun onFailure(call: Call<BranchSetupResponse>, t: Throwable) {
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

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected: " + item.itemId)
            //TODO: set visibility
            when (item.itemId) {
                R.id.branches -> {
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodMenu -> {
                    startActivity(Intent(this@BranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@BranchActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(this@BranchActivity, ManagerReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true

                }
                R.id.logout -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })
    }
}