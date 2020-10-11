package com.example.submissiontiga.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.submissiontiga.R
import com.example.submissiontiga.utils.AlarmReceiver
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(), View.OnClickListener {
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_cancel_repeating_alarm.setOnClickListener(this)
        btn_set_repeating_alarm.setOnClickListener(this)
        alarmReceiver = AlarmReceiver()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_set_repeating_alarm -> {
                alarmReceiver.setRepeatingAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING,
                    "09:00", resources.getString(R.string.one_time_alarm))
            }
            R.id.btn_cancel_repeating_alarm -> {
                alarmReceiver.cancelAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING)
            }
        }
    }
}