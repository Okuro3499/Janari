package com.movosoft.janari.Waiter


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.movosoft.janari.R


class waiter : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(com.movosoft.janari.R.layout.fragment_waiter, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val btn_wait = view.findViewById<Button>(com.movosoft.janari.R.id.btn_waiter)
        btn_wait.setOnClickListener{
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_layout2)
            dialog.setCancelable(false)
            val filter = dialog.findViewById<Button>(R.id.btnfilter)
            filter.setOnClickListener {
                dialog.dismiss()

            }
            dialog.show()

        }
    }
}