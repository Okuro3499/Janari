package com.movosoft.janari

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.movosoft.janari.cashier.CashierMenuActivity
import com.movosoft.janari.chef.ChefMenuActivity
import com.movosoft.janari.databinding.ActivityPinViewBinding
import com.movosoft.janari.manager.ManagerMenuActivity
import java.lang.String.format


class PinViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPinViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.bt0.setOnClickListener {
            binding.edtPassword.text = format("%s0", binding.edtPassword.text.toString())
        }

        binding.bt1.setOnClickListener {
            binding.edtPassword.text = format(
                "%s1",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt2.setOnClickListener {
            binding.edtPassword.text = format(
                "%s2",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt3.setOnClickListener {
            binding.edtPassword.text = format(
                "%s3",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt4.setOnClickListener {
            binding.edtPassword.text = format(
                "%s4",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt5.setOnClickListener {
            binding.edtPassword.text = format(
                "%s5",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt6.setOnClickListener {
            binding.edtPassword.text = format(
                "%s6",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt8.setOnClickListener {
            binding.edtPassword.text = format(
                "%s7",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt8.setOnClickListener {
            binding.edtPassword.text = format(
                "%s8",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt9.setOnClickListener {
            binding.edtPassword.text = format(
                "%s9",
                binding.edtPassword.text.toString()
            )
        }

        binding.btCancel.setOnClickListener {
            var str: String = binding.edtPassword.text.toString()
            if (str.length > 1) {
                str = str.substring(0, str.length - 1)
                binding.edtPassword.text = str
            } else {
                str.length
                binding.edtPassword.text = ""
            }
        }

        binding.btOk.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtPassword.text.toString().trim())) {
                binding.err.text = "Enter your PIN!"
            } else if (binding.edtPassword.text.toString().trim().length < 4) {
                binding.err.text = "PIN must be 4 digits!"
            } else if (binding.edtPassword.text.toString() == "0000") {
                startActivity(
                    Intent(
                        this,
                        ManagerMenuActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        ChefMenuActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }
        binding.forgotPin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CashierMenuActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

}