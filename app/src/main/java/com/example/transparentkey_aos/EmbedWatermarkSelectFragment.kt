package com.example.transparentkey_aos

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.transparentkey_aos.databinding.FragmentEmbedWatermarkSelectBinding
import com.example.transparentkey_aos.databinding.FragmentEmbedSelectBinding

class EmbedWatermarkSelectFragment : Fragment() {
    lateinit var binding: FragmentEmbedWatermarkSelectBinding
    lateinit var selectedImg: Bitmap
    private val REQUEST_KEY = "selected_img" // 데이터 요청 키

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmbedWatermarkSelectBinding.inflate(inflater, container, false)

//        Toast.makeText(context, "watermark select fragment", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnQr.setOnClickListener {
            setFragmentResult("wmSelection", bundleOf("selection" to 1))
            showDialog()
        }
        binding.btnImg.setOnClickListener {
//            setFragmentResult("wmSelection", bundleOf("selection" to 2))
//            replaceFragment(EmbedImageSelectFragment())
            Toast.makeText(context, "개발 중입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()

        // 이미지 수신
        @Suppress("DEPRECATION")
        setFragmentResultListener(REQUEST_KEY) { key, bundle ->
            val img: Bitmap? = bundle.getParcelable("selected_img")
            if (img != null) { // null이 아닐 때만 사용
                selectedImg = img
                binding.ivSelected.setImageBitmap(selectedImg) // 이미지 iv에 배치
            }
        }
    }

    /**
     * fragment replace
     */
    fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentCotainer, fragment)
            .commit()
    }

    /**
     * show dialog
     */
    private fun showDialog() {
        val dialogFragment = EmbedDialogFragment()
        dialogFragment.show(parentFragmentManager, "embedDialog")
    }




}