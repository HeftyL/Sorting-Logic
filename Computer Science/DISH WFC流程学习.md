# DISH WFC流程学习

## 概述

### 流程

```xml-dtd
                   WFC 激活流程                              


【阶段一：UI 入口与页面导航】
─────────────────────────────────────────────────────────────────
 1. Settings -> Network & Internet -> WFC 选项                    
    └─> mobile_network_settings.xml                             
        └─> WifiCallingPreferenceController                      
            └─> Intent -> WifiCallingSettingsActivity           
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 2. WifiCallingSettings (容器 Fragment)                          
    ├─> ViewPager + TabLayout (多 SIM 卡支持)                    
    └─> WifiCallingViewPagerAdapter                              
        └─> 创建 WifiCallingSettingsForSub Fragment              
            └─> 传递 subId                                       
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 3. WifiCallingSettingsForSub (具体设置页面)                      
    ├─> onCreate(): 初始化 UI 控件                              
    │   ├─> mSwitchBar (主开关)                                 
    │   ├─> mButtonWfcMode (WFC 模式选择)                        
    │   ├─> mButtonWfcRoamingMode (漫游模式)                     
    │   └─> mUpdateAddress (E911 地址更新)                       
    └─> updateBody(): 初始化 UI 状态                            
─────────────────────────────────────────────────────────────────


【阶段二：用户操作与 Entitlement 检查】
                        ↓
─────────────────────────────────────────────────────────────────
 4. 用户点击 WFC Switch                                          
    ├─> onCheckedChanged(isChecked)                              
    │                                                             
    ├─> [关闭] → updateWfcMode(false) → 直接关闭                
    │                                                             
    └─> [开启] → 显示免责声明                                    
        └─> WifiCallingDisclaimerFragment                        
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 5. 免责声明确认后                                                
    ├─> getCarrierActivityIntent()                               
    │   └─> 读取 CarrierConfig                                   
    │       └─> KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING    
    │           └─> "com.android.imsserviceentitlement/.WfcActivationActivity" 
    │                                                             
    └─> startActivityForResult(carrierAppIntent)                 
        └─> 启动 ImsServiceEntitlement 应用                      
─────────────────────────────────────────────────────────────────

【阶段三：ImsServiceEntitlement 激活流程】
                        ↓
─────────────────────────────────────────────────────────────────
 6. WfcActivationActivity.onCreate()                             
    ├─> createDependeny()                                       
    │   ├─> 提取 subId                                           
    │   └─> 创建 WfcActivationController                         
    │       └─> 创建 ImsEntitlementApi                           
    │           └─> 读取 CarrierConfig                           
    │               └─> KEY_ENTITLEMENT_SERVER_URL_STRING        
    │                                                             
    └─> mWfcActivationController.startFlow()                     
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 7. Entitlement 状态检查                                          
    ├─> evaluateEntitlementStatus()                              
    │   └─> EntitlementUtils.entitlementCheck()                  
    │       └─> ImsEntitlementApi.checkEntitlementStatus()       
    │           ├─> 构建 HTTP 请求                                
    │           │   ├─> 认证 Token                                
    │           │   ├─> FCM Token                                
    │           │   ├─> Entitlement Version                      
    │           │   └─> Configuration Version                    
    │           │                                                 
    │           ├─> 发送 HTTP 请求到运营商服务器                  
    │           │   └─> ServiceEntitlement.queryEntitlementStatus() 
    │           │                                                 
    │           └─> 解析 XML 响应                                 
    │               └─> toEntitlementResult()                    
    │                   ├─> 解析 VoWiFi/VoLTE/VoNR/SMSoIP 状态   
    │                   └─> 提取 E911 地址 URL 和 POST 数据        
    │                                                             
    └─> handleInitialEntitlementStatus(result)                   
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 8. 处理 Entitlement 结果                                        
    ├─> [激活流程] → handleEntitlementStatusForActivation()     
    │   ├─> vowifiEntitled() → 返回 RESULT_OK                    
    │   ├─> serverDataMissing() → 显示 WebView                   
    │   │   ├─> 优先显示条款页面 (如有)                          
    │   │   └─> 否则显示 E911 地址页面                            
    │   └─> incompatible() → 显示错误                            
    │                                                             
    └─> [更新流程] → handleEntitlementStatusForUpdating()       
        └─> 类似处理逻辑,开启webview更新e911地址                                          
─────────────────────────────────────────────────────────────────

【阶段四：E911 地址更新 (WebView)】
                        ↓
─────────────────────────────────────────────────────────────────
 9. 显示 WebView                                                 
    ├─> mActivationUi.showWebview(url, postData)                 
    │   └─> WfcActivationActivity.showWebview()                   
    │       └─> FragmentTransaction.replace()                    
    │           └─> WfcWebPortalFragment                         
    │                                                             
    ├─> WfcWebPortalFragment.onCreateView()                      
    │   ├─> 配置 WebView                                         
    │   ├─> 添加 JavaScript 接口 (VoWiFiWebServiceFlow)          
    │   └─> 加载 URL (GET 或 POST)                               
    │                                                             
    └─> 用户填写/更新 E911 地址                                  
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 10. WebView 回调处理                                             
     ├─> [用户取消] → dismissFlow()                              
     │   └─> 返回 RESULT_CANCELED                                
     │                                                             
     └─> [完成提交] → entitlementChanged()                       
         └─> finishFlow()                                         
             └─> reevaluateEntitlementStatus()                    
                 └─> 重新查询服务器状态                           
                     └─> handleEntitlementStatusAfterUpdating()  
                         ├─> vowifiEntitled() → RESULT_OK         
                         ├─> serverDataMissing() → 关闭 WFC       
                         └─> 其他 → 显示错误                      
─────────────────────────────────────────────────────────────────

【阶段五：返回 Settings 并开启 WFC】
                        ↓
─────────────────────────────────────────────────────────────────
 11. Settings 接收结果                                            
     ├─> onActivityResult(RESULT_OK)                             
     └─> updateWfcMode(true)                                     
         └─> mImsMmTelManager.setVoWiFiSettingEnabled(true)      
─────────────────────────────────────────────────────────────────

【阶段六：IMS 框架更新 WFC 能力】
                        ↓
─────────────────────────────────────────────────────────────────
 12. ImsMmTelManager → Telephony Service                         
     ├─> ITelephony.setVoWiFiSettingEnabled(subId, true)        
     └─> PhoneInterfaceManager.setVoWiFiSettingEnabled()         
         ├─> 权限检查                                             
         └─> ImsManager.getInstance().setWfcSetting(true)        
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 13. ImsManager.setWfcSetting()                                  
     ├─> 检查 Provisioning 状态                                  
     ├─> 写入 SubscriptionManager 数据库                         
     │   └─> WFC_IMS_ENABLED = "1"                              
     │                                                             
     ├─> updateVoiceWifiFeatureAndProvisionedValues()            
     │   ├─> 检查 WFC 状态 (available/enabled/provisioned)       
     │   ├─> 创建 CapabilityChangeRequest                        
     │   │   └─> 添加 CAPABILITY_TYPE_VOICE + REG_TECH_IWLAN     
     │   ├─> setWfcModeInternal()                                
     │   │   └─> ImsConfig.setConfig(KEY_VOICE_OVER_WIFI_MODE_OVERRIDE) 
     │   └─> setWfcRoamingSettingInternal()                      
     │       └─> ImsConfig.setConfig(KEY_VOICE_OVER_WIFI_ROAMING_ENABLED_OVERRIDE) 
     │                                                             
     ├─> changeMmTelCapability(request)                           
     │   └─> MmTelFeatureConnection.changeEnabledCapabilities()   
     │                                                             
     └─> turnOnIms() (确保 IMS 服务开启)                         
─────────────────────────────────────────────────────────────────
                        ↓
─────────────────────────────────────────────────────────────────
 14. IMS 服务处理能力变更                                         
     ├─> Binder 调用链                                            
     │   MmTelFeatureConnection.changeEnabledCapabilities()      
     │   → IImsMmTelFeature.changeCapabilitiesConfiguration()     
     │   → MmTelFeature.Stub.changeCapabilitiesConfiguration()   
     │   → MmTelFeature.requestChangeEnabledCapabilities()       
     │   → MmTelFeature.changeEnabledCapabilities()              
     │                                                             
     └─> ImsServiceSub.changeEnabledCapabilities()                
         ├─> 解析 CapabilityChangeRequest                         
         ├─> 构建 CapabilityStatus 列表                          
         └─> ImsConfigImpl.setCapabilityValue()                  
             └─> mCi.setServiceStatus() → Modem                  
─────────────────────────────────────────────────────────────────

【阶段七：UI 状态更新】
                        ↓
─────────────────────────────────────────────────────────────────
 15. 更新 UI 状态                                                 
     ├─> updateButtonWfcMode(wfcEnabled, wfcMode, wfcRoamingMode) 
     │   ├─> 更新 WFC 模式按钮摘要                                
     │   ├─> 设置按钮启用状态                                     
     │   ├─> 控制 Preference 可见性                              
     │   └─> updateDescriptionForOptions()                       
     │                                                             
     └─> 记录 Metrics                                             
─────────────────────────────────────────────────────────────────

                        ↓
                  【流程完成】
```



### 配置

#### wfc关键配置

1. ```xml
   <!-- wfc关键配置 -->
   
   <!-- 指定运营商是否支持 WFC over IMS（WiFi通话），独立于运营商配置。false=硬禁用，true=取决于运营商配置和可用性 -->
   <boolean name="carrier_wfc_ims_available_bool" value="true"/>
   
   <!-- WFC over IMS 的默认模式：0=仅WiFi，1=优先移动网络，2=优先WiFi，4=IMS优先 -->
   <int name="carrier_default_wfc_ims_mode_int" value="2" />
   
   <!-- 控制是否在dialer应用中显示 WiFi 通话按钮 -->
   <boolean name="com.google.android.dialer.display_wifi_calling_button_bool" value="true"/>
   
    <!-- 控制是否使用 WebView 显示 VoWiFi 门户页面（true=使用WebView，false=使用Chrome Custom Tabs） -->
   <boolean name="imsserviceentitlement.show_vowifi_webview_bool" value="true" />
   
   <!-- Entitlement和E911 地址管理应用 -->
   <string name="wfc_emergency_address_carrier_app_string">com.android.imsserviceentitlement/.WfcActivationActivity</string>
   
   <!-- Entitlement 服务器 URL -->
   <string name="imsserviceentitlement.entitlement_server_url_string">https://aes.mnc340.mcc313.pub.3gppnetwork.org/</string>
   
   <!-- WiFi通话时状态栏显示的SPN（服务提供商名称）格式索引
       可用格式索引值：
       0: %s（仅显示运营商名称）
       1: %s Wi-Fi Calling（运营商名称 + Wi-Fi Calling）
       2: WLAN Call（仅显示WLAN Call）
       3: %s WLAN Call（运营商名称 + WLAN Call）
       4: %s Wi-Fi（运营商名称 + Wi-Fi，中文环境下显示为"运营商名称 WLAN"）
       5: WiFi Calling | %s（WiFi Calling | 运营商名称）
       6: %s VoWifi（运营商名称 + VoWifi）
       7: Wi-Fi Calling（仅显示Wi-Fi Calling）
       8: Wi-Fi（仅显示Wi-Fi）
       9: WiFi Calling（仅显示WiFi Calling，无连字符）
       10: VoWifi（仅显示VoWifi）
       11: %s WiFi Calling（运营商名称 + WiFi Calling，无连字符）
       12: WiFi Call（仅显示WiFi Call）
       当前值4表示格式为"运营商名称 Wi-Fi"（例如："DISH Wi-Fi"），在中文环境下显示为"运营商名称 WLAN" -->
   <int name="wfc_spn_format_idx_int" value="4"/>
   
   <!-- WFC 模式是否可编辑 -->
   <boolean name="editable_wfc_mode_bool" value="true" />
   
   <!-- WFC 漫游模式是否可编辑 -->
   <boolean name="editable_wfc_roaming_mode_bool" value="true" />
   
   
   ```

#### DISH 5G具体配置

1. ```xml
   /home/project/liaohaifei/share/651/LA.QSSI.16.0.r1/LINUX/android/packages/apps/CarrierConfig/res/xml/vendor.xml
   <!-- DISH 5G BEGIN-->
       <!---TINNO add by song.yang for UGFDDS-891 on DATE20230530 BEGIN-->
       <carrier_config  mcc="313" mnc="340" gid1="6530,6738,6332,6732,6336,6736,6538,6630,6830,7330,7530,6636,6836,6638,6838,8030,9030">
           <!-- TINNO add by youruo.mi for UGFDDS-1785 20230714 begin -->
           <string-array name="carrier_vvm_package_name_string_array" num="1">
               <item value="com.dish.vvm"/>
           </string-array>
           <!-- TINNO end-->
           <boolean name="carrier_2g3g_roming_disable" value="true"/>
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients BEGIN -->
           <int name="recipientLimit" value="20"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size BEGIN -->
           <int name="maxMessageSize" value="1073152"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN BEGIN -->
           <string name="httpParams">X-MDN: ##LINE1##</string>
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN END -->
   
           <!-- TINNO add by song.yang for UGFDDS-881 20230602 BEGIN -->
           <boolean name="mtk_domestic_roaming_enabled_only_by_mobile_data_setting" value="true" />
           <boolean name="mtk_intl_roaming_enabled_only_by_roaming_data_setting" value="true" />
           <!-- TINNO add by song.yang for UGJFDS-976 20230602 END -->
   
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 BEGIN -->
           <boolean name="editable_enhanced_4g_lte_bool" value="false" />
           <boolean name="vonr_enabled_bool" value="true" />
           <boolean name="vonr_setting_visibility_bool" value="false" />
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 END -->
            <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT BEGIN -->
           <boolean name="carrier_volte_available_bool" value="true"/>
           <boolean name="rtt_supported_bool" value="true"/>
           <boolean name="ignore_rtt_mode_setting_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_for_vt_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 END -->
           <boolean name="rtt_upgrade_supported_bool" value="true"/>
           <boolean name="rtt_downgrade_supported_bool" value="true"/>
           <boolean name="mtk_rtt_video_switch_supported_bool" value="true"/>
           <boolean name="mtk_rtt_auto_accept_request_bool" value="true"/>
           <boolean name="mtk_multi_rtt_calls_supported_bool" value="true"/>
           <boolean name="allow_merging_rtt_calls_bool" value="true"/>
           <boolean name="mtk_rtt_audio_indication_supported_bool" value="true"/>
           <boolean name="mtk_rtt_ignore_roaming_check_bool" value="true"/>
           <boolean name="carrier_volte_tty_supported_bool" value="false"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT END -->
           <boolean name="carrier_wfc_ims_available_bool" value="true"/>
           <int name="carrier_default_wfc_ims_mode_int" value="2" />
           <boolean name="com.google.android.dialer.display_wifi_calling_button_bool" value="true"/>
           <boolean name="imsserviceentitlement.show_vowifi_webview_bool" value="true" />
           <string name="wfc_emergency_address_carrier_app_string">com.android.imsserviceentitlement/.WfcActivationActivity</string>
           <string name="imsserviceentitlement.entitlement_server_url_string">https://aes.mnc340.mcc313.pub.3gppnetwork.org/</string>
           
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag BEGIN -->
           <int name="wfc_spn_format_idx_int" value="4"/>
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag END -->
   
           <!--TINNO.TELECOM.RD.SUPPORT SIGNAL add by jian.wei for UGFDDS-624 BEGIN-->
           <int-array name="lte_rsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <int-array name="5g_nr_ssrsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <!--TINNO END-->
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 BEGIN -->
           <boolean name="support_video_conference_call_bool" value="true"/>
           <boolean name="notify_vt_handover_to_wifi_failure_bool" value="true"/>
           <boolean name="notify_handover_video_from_lte_to_wifi_bool" value="true"/>
           <boolean name="notify_handover_video_from_wifi_to_lte_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 END -->
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 BEGIN-->
           <string-array name="non_roaming_operator_string_array" num="7">
               <item value="310"/>
               <item value="311"/>
               <item value="312"/>
               <item value="313"/>
               <item value="314"/>
               <item value="315"/>
               <item value="316"/>
           </string-array>
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 END-->
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_while_roaming_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm BEGIN -->
           <string name="vvm_type_string">vvm_type_disable</string>
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm END -->
       </carrier_config>
   
        <carrier_config  mcc="208" mnc="09" gid1="6530,6738,6332,6732,6336,6736,6538,6630,6830,7330,7530,6636,6836,6638,6838,8030,9030">
           <!-- TINNO add by youruo.mi for UGFDDS-1785 20230714 begin -->
           <string-array name="carrier_vvm_package_name_string_array" num="1">
               <item value="com.dish.vvm"/>
           </string-array>
           <!-- TINNO end -->
   
           <boolean name="carrier_2g3g_roming_disable" value="true"/>
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients BEGIN -->
           <int name="recipientLimit" value="20"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size BEGIN -->
           <int name="maxMessageSize" value="1073152"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN BEGIN -->
           <string name="httpParams">X-MDN: ##LINE1##</string>
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN END -->
   
           <!-- TINNO add by song.yang for UGFDDS-881 20230602 BEGIN -->
           <boolean name="mtk_domestic_roaming_enabled_only_by_mobile_data_setting" value="true" />
           <boolean name="mtk_intl_roaming_enabled_only_by_roaming_data_setting" value="true" />
           <!-- TINNO add by song.yang for UGJFDS-976 20230602 END -->
   
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 BEGIN -->
           <boolean name="editable_enhanced_4g_lte_bool" value="false" />
           <boolean name="vonr_enabled_bool" value="true" />
           <boolean name="vonr_setting_visibility_bool" value="false" />
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 END -->
            <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT BEGIN -->
           <boolean name="carrier_volte_available_bool" value="true"/>
           <boolean name="rtt_supported_bool" value="true"/>
           <boolean name="ignore_rtt_mode_setting_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_for_vt_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 END -->
           <boolean name="rtt_upgrade_supported_bool" value="true"/>
           <boolean name="rtt_downgrade_supported_bool" value="true"/>
           <boolean name="mtk_rtt_video_switch_supported_bool" value="true"/>
           <boolean name="mtk_rtt_auto_accept_request_bool" value="true"/>
           <boolean name="mtk_multi_rtt_calls_supported_bool" value="true"/>
           <boolean name="allow_merging_rtt_calls_bool" value="true"/>
           <boolean name="mtk_rtt_audio_indication_supported_bool" value="true"/>
           <boolean name="mtk_rtt_ignore_roaming_check_bool" value="true"/>
           <boolean name="carrier_volte_tty_supported_bool" value="false"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT END -->
           <boolean name="carrier_wfc_ims_available_bool" value="true"/>
           <int name="carrier_default_wfc_ims_mode_int" value="2" />
           <boolean name="com.google.android.dialer.display_wifi_calling_button_bool" value="true"/>
           <boolean name="imsserviceentitlement.show_vowifi_webview_bool" value="true" />
           <string name="wfc_emergency_address_carrier_app_string">com.android.imsserviceentitlement/.WfcActivationActivity</string>
           <string name="imsserviceentitlement.entitlement_server_url_string">https://aes.mnc340.mcc313.pub.3gppnetwork.org/</string>
           
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag BEGIN -->
           <int name="wfc_spn_format_idx_int" value="4"/>
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag END -->
   
           <!--TINNO.TELECOM.RD.SUPPORT SIGNAL add by jian.wei for UGFDDS-624 BEGIN-->
           <int-array name="lte_rsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <int-array name="5g_nr_ssrsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <!--TINNO END-->
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 BEGIN -->
           <boolean name="support_video_conference_call_bool" value="true"/>
           <boolean name="notify_vt_handover_to_wifi_failure_bool" value="true"/>
           <boolean name="notify_handover_video_from_lte_to_wifi_bool" value="true"/>
           <boolean name="notify_handover_video_from_wifi_to_lte_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_while_roaming_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm BEGIN -->
           <string name="vvm_type_string">vvm_type_disable</string>
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm END -->
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 BEGIN-->
           <string-array name="non_roaming_operator_string_array" num="7">
               <item value="310"/>
               <item value="311"/>
               <item value="312"/>
               <item value="313"/>
               <item value="314"/>
               <item value="315"/>
               <item value="316"/>
           </string-array>
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 END-->
       </carrier_config>
   
        <carrier_config  mcc="302" mnc="220" gid1="6530,6738,6332,6732,6336,6736,6538,6630,6830,7330,7530,6636,6836,6638,6838,8030,9030">
           <!-- TINNO add by youruo.mi for UGFDDS-1785 20230714 begin -->
           <string-array name="carrier_vvm_package_name_string_array" num="1">
               <item value="com.dish.vvm"/>
           </string-array>
           <!-- TINNO end-->
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 BEGIN-->
           <string-array name="non_roaming_operator_string_array" num="7">
               <item value="310"/>
               <item value="311"/>
               <item value="312"/>
               <item value="313"/>
               <item value="314"/>
               <item value="315"/>
               <item value="316"/>
           </string-array>
           <!---TINNO add by song.yang for UGJFDS-4967 on DATE20230802 END-->
   
           <boolean name="carrier_2g3g_roming_disable" value="true"/>
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients BEGIN -->
           <int name="recipientLimit" value="20"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-736 Haifei.Liao 20230531 Maximum Number of Recipients END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size BEGIN -->
           <int name="maxMessageSize" value="1073152"/>
           <!-- TINNO.TELECOM.RD.SUPPORT SMS UGFDDS-737 Haifei.Liao 20230531 MMS Attachment File Size END -->
   
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN BEGIN -->
           <string name="httpParams">X-MDN: ##LINE1##</string>
           <!-- TINNO.TELECOM.RD.SUPPORT MMS UGJFDS-1262 mingyue.wang 20230606 X-MDN END -->
   
           <!-- TINNO add by song.yang for UGFDDS-881 20230602 BEGIN -->
           <boolean name="mtk_domestic_roaming_enabled_only_by_mobile_data_setting" value="true" />
           <boolean name="mtk_intl_roaming_enabled_only_by_roaming_data_setting" value="true" />
           <!-- TINNO add by song.yang for UGJFDS-976 20230602 END -->
   
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 BEGIN -->
           <boolean name="editable_enhanced_4g_lte_bool" value="false" />
           <boolean name="vonr_enabled_bool" value="true" />
           <boolean name="vonr_setting_visibility_bool" value="false" />
           <!-- TINNO add by song.yang for UGFDDS-1284 20230605 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT BEGIN -->
           <boolean name="carrier_volte_available_bool" value="true"/>
           <boolean name="rtt_supported_bool" value="true"/>
           <boolean name="ignore_rtt_mode_setting_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_for_vt_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5140 yingwei.wang 20230807 END -->
           <boolean name="rtt_upgrade_supported_bool" value="true"/>
           <boolean name="rtt_downgrade_supported_bool" value="true"/>
           <boolean name="mtk_rtt_video_switch_supported_bool" value="true"/>
           <boolean name="mtk_rtt_auto_accept_request_bool" value="true"/>
           <boolean name="mtk_multi_rtt_calls_supported_bool" value="true"/>
           <boolean name="allow_merging_rtt_calls_bool" value="true"/>
           <boolean name="mtk_rtt_audio_indication_supported_bool" value="true"/>
           <boolean name="mtk_rtt_ignore_roaming_check_bool" value="true"/>
           <boolean name="carrier_volte_tty_supported_bool" value="false"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT jia.chen 20230605 enable RTT END -->
           <boolean name="carrier_wfc_ims_available_bool" value="true"/>
           <int name="carrier_default_wfc_ims_mode_int" value="2" />
           <boolean name="com.google.android.dialer.display_wifi_calling_button_bool" value="true"/>
           <boolean name="imsserviceentitlement.show_vowifi_webview_bool" value="true" />
           <string name="wfc_emergency_address_carrier_app_string">com.android.imsserviceentitlement/.WfcActivationActivity</string>
           <string name="imsserviceentitlement.entitlement_server_url_string">https://aes.mnc340.mcc313.pub.3gppnetwork.org/</string>
           
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag BEGIN -->
           <int name="wfc_spn_format_idx_int" value="4"/>
           <!-- TINNO.TELECOM.RD.SUPPORT WFC UGFBDS-1405 haifei.liao Wi-Fi Calling Alpha Tag END -->
           
           <!--TINNO.TELECOM.RD.SUPPORT SIGNAL add by jian.wei for UGFDDS-624 BEGIN-->
           <int-array name="lte_rsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <int-array name="5g_nr_ssrsrp_thresholds_int_array" num="4">
               <item value="-117"/>
               <item value="-115"/>
               <item value="-110"/>
               <item value="-100"/>
           </int-array>
           <!--TINNO END-->
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 BEGIN -->
           <boolean name="support_video_conference_call_bool" value="true"/>
           <boolean name="notify_vt_handover_to_wifi_failure_bool" value="true"/>
           <boolean name="notify_handover_video_from_lte_to_wifi_bool" value="true"/>
           <boolean name="notify_handover_video_from_wifi_to_lte_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT VIDEO CALL UGJFDS-1701 yingwei.wang 20230615 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 BEGIN -->
           <boolean name="rtt_supported_while_roaming_bool" value="true"/>
           <!-- TINNO.TELECOM.RD.SUPPORT RTT UGJFDS-5070 yingwei.wang 20230807 END -->
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm BEGIN -->
           <string name="vvm_type_string">vvm_type_disable</string>
           <!-- TINNO.TELECOM.RD.SUPPORT VVM UGFDDS-2785 Haifei.Liao 20230820 remove native vvm END -->
       </carrier_config>
       <!---TINNO add by song.yang for UGFDDS-891 on DATE20230530 END-->
       <!-- DISH 5G END-->
   ```

   



## 详细流程追踪

### 入口:Setting->Network&Internet->Wifi Calling选项开关

1. ```xml
   /home/project/liaohaifei/share/651/LA.QSSI.16.0.r1/LINUX/android/packages/apps/Settings/res/xml/mobile_network_settings.xml       
               <!-- Settings search is handled by WifiCallingSearchItem. -->
               <Preference
                   android:key="wifi_calling"
                   android:title="@string/wifi_calling_settings_title"
                   android:summary="@string/wifi_calling_summary"
                   settings:searchable="false"
                   settings:controller="com.android.settings.network.telephony.WifiCallingPreferenceController">
                   <intent android:action="android.intent.action.MAIN"
                           android:targetPackage="com.android.settings"
                           android:targetClass="com.android.settings.Settings$WifiCallingSettingsActivity">
                       <extra android:name="show_drawer_menu" android:value="true"/>
                   </intent>
               </Preference>
   ```

2. WifiCallingSettings → WifiCallingSettingsForSub 页面配置流程

   - ```java
     WifiCallingSettings 是容器 Fragment，使用 ViewPager 管理多个 WifiCallingSettingsForSub Fragment（每个 SIM 卡一个）。
     /**
      * "Wi-Fi Calling settings" screen. This is the container fragment which holds
      * {@link WifiCallingSettingsForSub} fragments.
      */
     public class WifiCallingSettings extends SettingsPreferenceFragment
     
     第一步：WifiCallingSettings 初始化
         @Override
         public void onCreate(Bundle icicle) {
             mConstructionSubId = getConstructionSubId(icicle);
             super.onCreate(icicle);
             if (MobileNetworkUtils.isMobileNetworkUserRestricted(getActivity())) {
                 finish();
                 return;
             }
             Log.d(TAG, "SubId=" + mConstructionSubId);
     
             if (mConstructionSubId != SubscriptionManager.INVALID_SUBSCRIPTION_ID) {
                 // Only config Wfc if it's enabled by platform.
                 mSubscriptionChangeListener = getSubscriptionChangeListener(getContext());
             }
             mSil = updateSubList();
         }
         //getConstructionSubId()：从 Intent 或 Bundle 获取目标 subId
     	//updateSubList()：筛选支持 WFC 的订阅列表
     	
     第二步：创建 ViewPager 和 Adapter
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         if (MobileNetworkUtils.isMobileNetworkUserRestricted(getActivity())) {
             return new ViewStub(getActivity());
         }
         final View view = inflater.inflate(R.layout.wifi_calling_settings_tabs, container, false);
     
         mTabLayout = view.findViewById(R.id.sliding_tabs);
         mViewPager = (RtlCompatibleViewPager) view.findViewById(R.id.view_pager);
     
         mPagerAdapter = new WifiCallingViewPagerAdapter(getChildFragmentManager(), mViewPager);
         mViewPager.setAdapter(mPagerAdapter);
         mViewPager.addOnPageChangeListener(new InternalViewPagerListener());
         maybeSetViewForSubId();
         return view;
     }
     //加载 wifi_calling_settings_tabs 布局（包含 ViewPager 和 TabLayout）
     //创建 WifiCallingViewPagerAdapter，使用 getChildFragmentManager() 管理子 Fragment
     
     第三步：Adapter 创建 WifiCallingSettingsForSub Fragment
     @VisibleForTesting
     final class WifiCallingViewPagerAdapter extends FragmentPagerAdapter {
         private final RtlCompatibleViewPager mViewPager;
     
         public WifiCallingViewPagerAdapter(FragmentManager fragmentManager,
                 RtlCompatibleViewPager viewPager) {
             super(fragmentManager);
             mViewPager = viewPager;
         }
     
         @Override
         public CharSequence getPageTitle(int position) {
             return String.valueOf(SubscriptionUtil.getUniqueSubscriptionDisplayName(
                     mSil.get(position), getContext()));
         }
     
         @Override
         public Fragment getItem(int position) {
             int subId = mSil.get(position).getSubscriptionId();
             Log.d(TAG, "Adapter getItem " + position + " for subId=" + subId);
             final Bundle args = new Bundle();
             args.putBoolean(SearchMenuController.NEED_SEARCH_ICON_IN_ACTION_BAR, false);
             args.putInt(WifiCallingSettingsForSub.FRAGMENT_BUNDLE_SUBID, subId);
             final WifiCallingSettingsForSub fragment = new WifiCallingSettingsForSub();
             fragment.setArguments(args);
     
             return fragment;
         }
     //getItem(position)：ViewPager 需要 Fragment 时调用
     //从 mSil 获取对应位置的 SubscriptionInfo，提取 subId
     //创建 Bundle，放入 FRAGMENT_BUNDLE_SUBID
     //创建 WifiCallingSettingsForSub 实例
     //通过 setArguments() 传递 Bundle
         
     第四步：WifiCallingSettingsForSub 接收参数
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
     
         // SubId should always be specified when creating this fragment. Either through
         // fragment.setArguments() or through savedInstanceState.
         if (getArguments() != null && getArguments().containsKey(FRAGMENT_BUNDLE_SUBID)) {
             mSubId = getArguments().getInt(FRAGMENT_BUNDLE_SUBID);
         } else if (savedInstanceState != null) {
             mSubId = savedInstanceState.getInt(
                     FRAGMENT_BUNDLE_SUBID, SubscriptionManager.INVALID_SUBSCRIPTION_ID);
         }
     
         mImsMmTelManager = getImsMmTelManager();
     
         mSwitchBar = (SettingsMainSwitchPreference) findPreference(SWITCH_BAR);
     
         mButtonWfcMode = findPreference(BUTTON_WFC_MODE);
         mButtonWfcMode.setOnPreferenceChangeListener(this);
     
         mButtonWfcRoamingMode = findPreference(BUTTON_WFC_ROAMING_MODE);
         mButtonWfcRoamingMode.setOnPreferenceChangeListener(this);
     
         mUpdateAddress = findPreference(PREFERENCE_EMERGENCY_ADDRESS);
         mUpdateAddress.setOnPreferenceClickListener(mUpdateAddressListener);
     
         mIntentFilter = new IntentFilter();
         mIntentFilter.addAction(ImsManager.ACTION_WFC_IMS_REGISTRATION_ERROR);
     
         updateDescriptionForOptions(
                 List.of(mButtonWfcMode, mButtonWfcRoamingMode, mUpdateAddress));
     
         List<AbstractPreferenceController> subscriptionPreferenceControllers =
                 useGroup(AbstractSubscriptionPreferenceController.class);
         subscriptionPreferenceControllers.forEach(
                 controller -> ((AbstractSubscriptionPreferenceController) controller).init(mSubId));
     }
     
     //从 getArguments() 读取 FRAGMENT_BUNDLE_SUBID，设置 mSubId
     //初始化 ImsMmTelManager、UI 控件和监听器
         
     第五步：定位到指定 subId（可选）
     private void maybeSetViewForSubId() {
         if (mSil == null) {
             return;
         }
         int subId = mConstructionSubId;
         if (SubscriptionManager.isValidSubscriptionId(subId)) {
             for (SubscriptionInfo subInfo : mSil) {
                 if (subId == subInfo.getSubscriptionId()) {
                     mViewPager.setCurrentItem(mSil.indexOf(subInfo));
                     break;
                 }
             }
         }
     }
     //如果 Intent 指定了 EXTRA_SUB_ID，会定位到对应页面。
     
     第六步：Tab 显示控制
     @Override
     public void onStart() {
         super.onStart();
     
         if (mSil != null && mSil.size() > 1) {
             mTabLayout.setViewPager(mViewPager);
         } else {
             mTabLayout.setVisibility(View.GONE);
         }
     
         updateTitleForCurrentSub();
     
         if (mSubscriptionChangeListener != null) {
             mSubscriptionChangeListener.start();
         }
     }
     //多 SIM 时显示 TabLayout，单 SIM 时隐藏
     //更新标题为当前订阅名称
     ```

### 准备EntitlementCheck:点击WFC 开关进入ImsServiceEntitlement应用

- ```java
  第一步：用户点击 Settings 中的 WFC Switch
  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
      Log.d(TAG, "onSwitchChanged(" + isChecked + ")");
  
      if (!isChecked) {
          updateWfcMode(false);
          return;
      }
  
      // Launch disclaimer fragment before turning on WFC
      final Context context = getActivity();
      final Bundle args = new Bundle();
      args.putInt(EXTRA_SUB_ID, mSubId);
      new SubSettingLauncher(context)
              .setDestination(WifiCallingDisclaimerFragment.class.getName())
              .setArguments(args)
              .setTitleRes(R.string.wifi_calling_settings_title)
              .setSourceMetricsCategory(getMetricsCategory())
              .setResultListener(this, REQUEST_CHECK_WFC_DISCLAIMER)
              .launch();
  }
  //用户开启开关后，先显示免责声明页面。
  //如果是关闭,直接设置wfc模式为false后返回
  
  第二步：免责声明确认后，Settings 读取 CarrierConfig
  case REQUEST_CHECK_WFC_DISCLAIMER:
      if (resultCode == Activity.RESULT_OK) {
          // Call address management activity before turning on WFC
          final Intent carrierAppIntent = getCarrierActivityIntent();
          if (carrierAppIntent != null) {
              carrierAppIntent.putExtra(EXTRA_LAUNCH_CARRIER_APP, LAUNCH_APP_ACTIVATE);
              startActivityForResult(carrierAppIntent,
                      REQUEST_CHECK_WFC_EMERGENCY_ADDRESS);
          } else {
              updateWfcMode(true);
          }
      }
      break;
  //关键方法：getCarrierActivityIntent() 解析 CarrierConfig:
  static @Nullable Intent getCarrierActivityIntent(Context context, int subId) {
      // Retrieve component name from carrier config
      final CarrierConfigManager configManager =
              context.getSystemService(CarrierConfigManager.class);
      if (configManager == null) return null;
  
      final PersistableBundle bundle = configManager.getConfigForSubId(subId);
      if (bundle == null) return null;
  
      final String carrierApp = bundle.getString(
              CarrierConfigManager.KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING);
      if (TextUtils.isEmpty(carrierApp)) return null;
  
      final ComponentName componentName = ComponentName.unflattenFromString(carrierApp);
      if (componentName == null) return null;
  
      // Build and return intent
      final Intent intent = new Intent();
      intent.setComponent(componentName);
      intent.putExtra(SubscriptionManager.EXTRA_SUBSCRIPTION_INDEX, subId);
      return intent;
  }
  //解析过程：
  //1.获取 CarrierConfigManager
  //2.通过 getConfigForSubId(subId) 获取当前订阅的配置
  //3.读取 KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING（例如："com.android.imsserviceentitlement/.WfcActivationActivity"）
  //4.将字符串转换为 ComponentName
  //5.构建 Intent，附加 EXTRA_SUBSCRIPTION_INDEX
  
  第三步：启动 ImsServiceEntitlement 应用
  Settings 通过 startActivityForResult() 启动配置的 Activity：
  final Intent carrierAppIntent = getCarrierActivityIntent();
  if (carrierAppIntent != null) {
      carrierAppIntent.putExtra(EXTRA_LAUNCH_CARRIER_APP, LAUNCH_APP_ACTIVATE);
      startActivityForResult(carrierAppIntent,
              REQUEST_CHECK_WFC_EMERGENCY_ADDRESS);
  }
  //Intent 包含：
  //ComponentName: com.android.imsserviceentitlement/.WfcActivationActivity
  //EXTRA_SUBSCRIPTION_INDEX: 当前订阅 ID
  //EXTRA_LAUNCH_CARRIER_APP: LAUNCH_APP_ACTIVATE (0)
  
  //***后续在onActivityResult判断结果,并更新wfc模式
      @Override
      public void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          Log.d(TAG, "WFC activity request = " + requestCode + " result = " + resultCode);
          switch (requestCode) {
              case REQUEST_CHECK_WFC_EMERGENCY_ADDRESS:
                  if (resultCode == Activity.RESULT_OK) {
                      updateWfcMode(true);
                  }
                  break;
                  
  第四步：ImsServiceEntitlement 接收 Intent 并读取 CarrierConfig
  ///home/project/liaohaifei/share/651/LA.QSSI.16.0.r1/LINUX/android/packages/apps/ImsServiceEntitlement/src/com/android/imsserviceentitlement/WfcActivationActivity.java
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          createDependeny();
          setSuwTheme();
  
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_wfc_activation);
  
          if (mWfcActivationController.isSkipWfcActivation()
                  && ActivityConstants.isActivationFlow(getIntent())) {
              Log.d(TAG, "Skip wfc activation");
              setResultAndFinish(Activity.RESULT_OK);
              return;
          }
  
          mWfcActivationController.startFlow();
      }
  //createDependeny获取<string name="imsserviceentitlement.entitlement_server_url_string">https://aes.mnc340.mcc313.pub.3gppnetwork.org/</string>
  //isSkipWfcActivation()：检查 CarrierConfig KEY_SKIP_WFC_ACTIVATION_BOOL
  //isActivationFlow()：判断是否为激活流程（LAUNCH_APP_ACTIVATE）
  //若两者都为 true，直接返回 RESULT_OK 给 Settings，跳过后续流程      
                  
  private void createDependeny() {
      Log.d(TAG, "Loading dependencies...");
      // TODO(b/177495634) Use DependencyInjector
      if (sWfcActivationController == null) {
          // Default initialization
          Log.d(TAG, "Default WfcActivationController initialization");
          Intent startIntent = this.getIntent();
          int subId = ActivityConstants.getSubId(startIntent);
          mWfcActivationController =
                  new WfcActivationController(
                          /* context = */ this,
                          /* wfcActivationUi = */ this,
                          new ImsEntitlementApi(this, subId),
                          startIntent);
      } else {
          mWfcActivationController = sWfcActivationController;
      }
  }
  //从 Intent 中提取 subId，创建 ImsEntitlementApi。
  
  第五步：ImsEntitlementApi 读取 CarrierConfig 配置
  public ImsEntitlementApi(Context context, int subId) {
      this.mContext = context;
      this.mSubId = subId;
      CarrierConfig carrierConfig = getCarrierConfig(context);
      this.mNeedsImsProvisioning = TelephonyUtils.isImsProvisioningRequired(context, subId);
      this.mServiceEntitlement =
              new ServiceEntitlement(
                      context,
                      carrierConfig,
                      subId,
                      /* saveHttpHistory = */ false,
                      DebugUtils.getBypassEapAkaResponse());
      this.mLastEntitlementConfiguration = new EntitlementConfiguration(context, subId);
  }
  //getCarrierConfig() 方法读取服务器 URL：
  private CarrierConfig getCarrierConfig(Context context) {
      String entitlementServiceUrl = TelephonyUtils.getEntitlementServerUrl(context, mSubId);
      return CarrierConfig.builder()
              .setClientTs43(CarrierConfig.CLIENT_TS_43_IMS_ENTITLEMENT)
              .setServerUrl(entitlementServiceUrl)
              .build();
  }
  //TelephonyUtils.getEntitlementServerUrl() 从 CarrierConfig 读取：
  public static String getEntitlementServerUrl(Context context, int subId) {
      return getConfigForSubId(context, subId).getString(
              CarrierConfigManager.ImsServiceEntitlement.KEY_ENTITLEMENT_SERVER_URL_STRING,
              ""
      );
  }
  //getConfigForSubId() 获取配置：
  private static PersistableBundle getConfigForSubId(Context context, int subId) {
      CarrierConfigManager carrierConfigManager =
              context.getSystemService(CarrierConfigManager.class);
      PersistableBundle carrierConfig = carrierConfigManager.getConfigForSubId(subId);
      if (carrierConfig == null) {
          Log.d(TAG, "getDefaultConfig");
          carrierConfig = CarrierConfigManager.getDefaultConfig();
      }
      return carrierConfig;
  }
  ```

### * **开始EntitlementCheck:`mWfcActivationController.startFlow()`**

```java
第一步：mWfcActivationController.startFlow() - 启动激活流程
    /** Indicates the controller to start WFC activation or emergency address update flow. */
    @MainThread
    public void startFlow() {
        showGeneralWaitingUi();
        evaluateEntitlementStatus();
        if (isActivationFlow()) {
            mMetricsLogger.start(IMS_SERVICE_ENTITLEMENT_UPDATED__PURPOSE__ACTIVATION);
        } else {
            mMetricsLogger.start(IMS_SERVICE_ENTITLEMENT_UPDATED__PURPOSE__UPDATE);
        }
    }
//showGeneralWaitingUi()：显示等待 UI
//evaluateEntitlementStatus()：评估 Entitlement 状态
//isActivationFlow()判断,记录 Metrics：根据流程类型记录统计日志

    /** Evaluates entitlement status for activation or update. */
    @MainThread
    public void evaluateEntitlementStatus() {
        if (!mTelephonyUtils.isNetworkConnected()) {
            handleInitialEntitlementStatus(null);
            return;
        }
        EntitlementUtils.entitlementCheck(
                mImsEntitlementApi,
                result -> mMainThreadHandler.post(
                        () -> handleInitialEntitlementStatus(result)));
    }
//先检查网络，无网络则直接处理为失败；
//有网络则调用 EntitlementUtils.entitlementCheck() 异步查询
//handleInitialEntitlementStatus(result)处理查询后的结果

第二步：EntitlementUtils.entitlementCheck() - 异步查询服务器
    /**
     * Performs the entitlement status check, and passes the result via {@link
     * EntitlementResultCallback}.
     */
    @MainThread
    public static void entitlementCheck(
            ImsEntitlementApi activationApi, EntitlementResultCallback callback) {
        sCheckEntitlementFuture =
                Futures.submit(() -> getEntitlementStatus(activationApi), getAsyncExecutor());
        Futures.addCallback(
                sCheckEntitlementFuture,
                new FutureCallback<EntitlementResult>() {
                    @Override
                    public void onSuccess(EntitlementResult result) {
                        callback.onEntitlementResult(result);
                        sCheckEntitlementFuture = null;
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.w(LOG_TAG, "get entitlement status failed.", t);
                        sCheckEntitlementFuture = null;
                    }
                },
                getDirectExecutor());
    }
//提交后台任务：Futures.submit() 在后台线程执行 getEntitlementStatus()
//设置回调：成功时调用 callback.onEntitlementResult(result)，失败时记录日志
    /**
     * Gets entitlement status via carrier-specific entitlement API over network; returns null on
     * network falure or other unexpected failure from entitlement API.
     */
    @WorkerThread
    @Nullable
    private static EntitlementResult getEntitlementStatus(ImsEntitlementApi activationApi) {
        try {
            return activationApi.checkEntitlementStatus();
        } catch (RuntimeException e) {
            Log.e("WfcActivationActivity", "getEntitlementStatus failed.", e);
            return null;
        }
    }
//ImsEntitlementApi.checkEntitlementStatus()中进行网络check

第三步：ImsEntitlementApi.checkEntitlementStatus() - HTTP 请求
@Nullable
public EntitlementResult checkEntitlementStatus() {
    Log.d(TAG, "checkEntitlementStatus subId=" + mSubId);
    ServiceEntitlementRequest.Builder requestBuilder = ServiceEntitlementRequest.builder();
    mLastEntitlementConfiguration.getToken().ifPresent(
            token -> requestBuilder.setAuthenticationToken(token));
    FcmUtils.fetchFcmToken(mContext, mSubId);
    requestBuilder.setNotificationToken(FcmTokenStore.getToken(mContext, mSubId));
    int entitlementVersion = TelephonyUtils.getEntitlementVersion(mContext, mSubId);
    requestBuilder.setEntitlementVersion(entitlementVersion + ".0");
    requestBuilder.setAcceptContentType(ServiceEntitlementRequest.ACCEPT_CONTENT_TYPE_XML);
    if (mNeedsImsProvisioning) {
        requestBuilder.setConfigurationVersion(
                Integer.parseInt(mLastEntitlementConfiguration.getVersion()));
    }
    ServiceEntitlementRequest request = requestBuilder.build();

    XmlDoc entitlementXmlDoc = null;

    try {
        String rawXml = mServiceEntitlement.queryEntitlementStatus(
                mNeedsImsProvisioning
                        ? ImmutableList.of(
                        ServiceEntitlement.APP_VOWIFI,
                        ServiceEntitlement.APP_VOLTE,
                        ServiceEntitlement.APP_SMSOIP)
                        : ImmutableList.of(ServiceEntitlement.APP_VOWIFI),
                request);
        entitlementXmlDoc = new XmlDoc(rawXml);
        mLastEntitlementConfiguration.update(entitlementVersion, rawXml);
        // Reset the retry count if no exception from queryEntitlementStatus()
        mRetryFullAuthenticationCount = AUTHENTICATION_RETRIES;
    } catch (ServiceEntitlementException e) {
        if (e.getErrorCode() == ServiceEntitlementException.ERROR_HTTP_STATUS_NOT_SUCCESS) {
            if (e.getHttpStatus() == RESPONSE_TOKEN_EXPIRED) {
                if (mRetryFullAuthenticationCount <= 0) {
                    Log.d(TAG, "Ran out of the retry count, stop query status.");
                    return null;
                }
                Log.d(TAG, "Server asking for full authentication, retry the query.");
                // Clean up the cached data and perform full authentication next query.
                mLastEntitlementConfiguration.reset();
                mRetryFullAuthenticationCount--;
                return checkEntitlementStatus();
            } else if (e.getHttpStatus() == RESPONSE_RETRY_AFTER && !TextUtils.isEmpty(
                    e.getRetryAfter())) {
                // For handling the case of HTTP_UNAVAILABLE(503), client would perform the
                // retry for the delay of Retry-After.
                Log.d(TAG, "Server asking for retry. retryAfter = " + e.getRetryAfter());
                boolean isDefaultActive = TelephonyUtils.getDefaultStatus(mContext, mSubId);
                return EntitlementResult.builder(isDefaultActive)
                        .setRetryAfterSeconds(parseDelaySecondsByRetryAfter(e.getRetryAfter()))
                        .build();
            }
        }
        Log.e(TAG, "queryEntitlementStatus failed", e);
    }
    return entitlementXmlDoc == null ? null : toEntitlementResult(entitlementXmlDoc);
}
//构建请求：获取并设置认证 Token、FCM Token、Entitlement Version、Content-Type、Configuration Version（如需要）
//发送请求：mServiceEntitlement.queryEntitlementStatus() 发送 HTTP 请求
//解析响应：将 XML 解析为 XmlDoc，并更新本地配置
//错误处理：
//    1.Token 过期（511）：重置配置并重试（最多 1 次）
//    2.服务不可用（503）：返回带 RetryAfter 的结果，由调用方延迟重试
//    3.其他错误：返回 null

第四步:mLastEntitlementConfiguration.getToken() - 获取认证 Token
/*
authenticationToken（认证令牌）是 IMS Service Entitlement 流程中用于快速认证（Fast Authentication）的令牌，由运营商服务器在首次完整认证后颁发，用于后续请求时避免重复进行完整的 EAP-AKA 认证。
完整认证（Full Authentication）- 无 Token：
1. 设备发送请求（无 authenticationToken）
2. 服务器要求 EAP-AKA 认证
3. 设备与服务器进行 EAP-AKA 握手
   - 服务器发送挑战（Challenge）
   - 设备使用 SIM 卡密钥计算响应
   - 服务器验证响应
4. 服务器返回结果 + 新 Token
    
快速认证（Fast Authentication）- 有 Token：
1. 设备发送请求（带 authenticationToken）
2. 服务器验证 Token 有效性
3. 如果 Token 有效 → 直接返回结果
4. 如果 Token 过期 → 返回 HTTP 511，要求完整认证
*/
    /**
     * Returns token stored in the {@link EntitlementConfigurationsDataStore} if it is in validity
     * period. Returns {@link Optional#empty()} if the token was expired or the value of token
     * validity not positive.
     */
    public Optional<String> getToken() {
        return isTokenInValidityPeriod()
                ? mXmlDoc.getFromToken(ResponseXmlAttributes.TOKEN)
                : Optional.empty();
    }


第五步:FcmUtils.fetchFcmToken(mContext, mSubId); - 获取认证 Token
 //获取 FCM（Firebase Cloud Messaging）Token，用于接收运营商推送的 Entitlement 状态变更通知
    
     /** Fetches FCM token, if it's not available via {@link FcmTokenStore#getToken}. */
    @WorkerThread
    public static void fetchFcmToken(Context context, int subId) {
        if (TelephonyUtils.getFcmSenderId(context, subId).isEmpty()) {
            return;
        }

        if (FcmTokenStore.hasToken(context, subId)) {
            Log.d(LOG_TAG, "FCM token available.");
            return;
        }

        Log.d(LOG_TAG, "FCM token unavailable. Try to update...");
        final CountDownLatch tokenUpdated = new CountDownLatch(1);
        final SharedPreferences.OnSharedPreferenceChangeListener listener =
                (s, k) -> {
                    Log.d(LOG_TAG, "FCM preference changed.");
                    if (FcmTokenStore.hasToken(context, subId)) {
                        tokenUpdated.countDown();
                    }
                };
        FcmTokenStore.registerTokenUpdateListener(context, listener);

        // Starts a JobIntentService to update FCM token by calling FCM API on a worker thread.
        FcmRegistrationService.enqueueJob(context);

        try {
            // Wait for 25s. If FCM token update failed/timeout, we will let user see
            // the error message returned by server. Then user can manually retry.
            if (tokenUpdated.await(TOKEN_UPDATE_WAITING_SECONDS, SECONDS)) {
                Log.d(LOG_TAG, "FCM token updated.");
            } else {
                Log.d(LOG_TAG, "FCM token update failed.");
            }
        } catch (InterruptedException e) {
            // Do nothing
            Log.d(LOG_TAG, "FCM token update interrupted.");
        }
        FcmTokenStore.unregisterTokenUpdateListener(context, listener);
    }

    
***第六步:toEntitlementResult 解析response xml为EntitlementResult
private EntitlementResult toEntitlementResult(XmlDoc doc) {
    boolean isDefaultActive = TelephonyUtils.getDefaultStatus(mContext, mSubId);
    EntitlementResult.Builder builder = EntitlementResult.builder(isDefaultActive);
    ClientBehavior clientBehavior = mLastEntitlementConfiguration.entitlementValidation();

    if (mNeedsImsProvisioning && isResetToDefault(clientBehavior)) {
        // keep the entitlement result in default value and reset the configs.
        mLastEntitlementConfiguration.reset(clientBehavior);
    } else {
        builder.setVowifiStatus(Ts43VowifiStatus.builder(doc).build())
                .setVolteStatus(Ts43VolteStatus.builder(doc).build())
                .setVonrStatus(Ts43VonrStatus.builder(doc).build())
                .setSmsoveripStatus(Ts43SmsOverIpStatus.builder(doc).build());
        doc.getFromVowifi(ResponseXmlAttributes.SERVER_FLOW_URL)
                .ifPresent(url -> builder.setEmergencyAddressWebUrl(url));
        doc.getFromVowifi(ResponseXmlAttributes.SERVER_FLOW_USER_DATA)
                .ifPresent(userData -> builder.setEmergencyAddressWebData(userData));
    }
    return builder.build();
}
//读取 CarrierConfig KEY_DEFAULT_SERVICE_ENTITLEMENT_STATUS_BOOL 作为默认状态(项目代码中不手动配置,取默认值false)
//1.获取默认状态并创建 Builder 
    /** Returns a new {@link Builder} object. */
    public static Builder builder(boolean isDefaultEnabled) {
        return new AutoValue_EntitlementResult.Builder()
                .setVowifiStatus(isDefaultEnabled ? ACTIVE_VOWIFI_STATUS : INACTIVE_VOWIFI_STATUS)
                .setVolteStatus(isDefaultEnabled ? ACTIVE_VOLTE_STATUS : INACTIVE_VOLTE_STATUS)
                .setSmsoveripStatus(
                        isDefaultEnabled ? ACTIVE_SMSOVERIP_STATUS : INACTIVE_SMSOVERIP_STATUS)
                .setVonrStatus(isDefaultEnabled ? ACTIVE_VONR_STATUS : INACTIVE_VONR_STATUS)
                .setEmergencyAddressWebUrl("")
                .setEmergencyAddressWebData("")
                .setTermsAndConditionsWebUrl("")
                .setRetryAfterSeconds(-1);
    }
//2.检查是否需要重置为默认值:仅在需要 IMS Provisioning 时检查,如果需要重置，保持默认值并重置配置，直接返回 Builder
//3.解析 XML 并构建各服务状态:解析VoWiFi,VoLTE,VoNR,SMSoIP的状态
//       1.解析 VoWiFi 状态
//			EntitlementStatus：0=DISABLED, 1=ENABLED, 2=INCOMPATIBLE, 3=PROVISIONING
//			TC_Status：条款状态
//			AddrStatus：E911 地址状态
//			ProvStatus：Provisioning 状态
//       2.解析 VoLTE 状态:提取 EntitlementStatus
//       3.解析 VoNR 状态:分别提取 Home 和 Roaming 的 EntitlementStatus 和 NetworkVoiceIRatCapability。
//       4.解析 SMSoIP 状态:提取 EntitlementStatus。
//4.从xml文件doc中提取 E911 地址注册页面 URL和POST 数据（如 token、设备 ID、订阅 ID 等）  
/*xml结构举例
<wap-provisioningdoc>
    <characteristic type="APPLICATION">
        <parm name="AppID" value="VOWIFI"/>
        <parm name="EntitlementStatus" value="0"/>
        <parm name="TC_Status" value="0"/>
        <parm name="AddrStatus" value="0"/>
        <parm name="ProvStatus" value="0"/>
        <parm name="ServiceFlow_URL" value="https://example.com/e911"/>
        <parm name="ServiceFlow_UserData" value="token=xxx&deviceId=yyy"/>
    </characteristic>
    <characteristic type="APPLICATION">
        <parm name="AppID" value="VOLTE"/>
        <parm name="EntitlementStatus" value="1"/>
    </characteristic>
    <characteristic type="RATVoiceEntitleInfoDetails">
        <parm name="AccessType" value="2"/>
        <parm name="HomeRoamingNWType" value="2"/>
        <parm name="EntitlementStatus" value="1"/>
    </characteristic>
</wap-provisioningdoc>
*/

第五步：callback调用 handleInitialEntitlementStatus() - 处理查询结果
    @MainThread
    private void handleInitialEntitlementStatus(@Nullable EntitlementResult result) {
        Log.d(TAG, "Initial entitlement result: " + result);
        if (result == null) {
            showGeneralErrorUi();
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__FAILED);
            return;
        }
        if (isActivationFlow()) {
            handleEntitlementStatusForActivation(result);
        } else {
            handleEntitlementStatusForUpdating(result);
        }
    }
//结果为空：显示通用错误并记录失败日志
//激活流程：调用 handleEntitlementStatusForActivation()
//更新流程：调用 handleEntitlementStatusForUpdating()

    /**
     * Returns {@code true} if the app is launched for WFC activation; {@code false} for emergency
     * address update.
     */
    private boolean isActivationFlow() {
        return ActivityConstants.isActivationFlow(mStartIntent);
    }

    /**
     * Returns {@code true} if the app is launched for WFC activation; {@code false} for emergency
     * address update or displaying terms & conditions.
     */
    public static boolean isActivationFlow(Intent intent) {
        int intention = getLaunchIntention(intent);
        Log.d(TAG, "Start Activity intention : " + intention);
        return intention == LAUNCH_APP_ACTIVATE;
    }

    /** Returns the launch intention extra in the {@code intent}. */
    public static int getLaunchIntention(Intent intent) {
        if (intent == null) {
            return LAUNCH_APP_ACTIVATE;
        }

        return intent.getIntExtra(EXTRA_LAUNCH_CARRIER_APP, LAUNCH_APP_ACTIVATE);
    }
//EXTRA_LAUNCH_CARRIER_APP的值来自点击WFC 开关进入ImsServiceEntitlement应用#第三步：启动 ImsServiceEntitlement 应用,此处是激活
// carrierAppIntent.putExtra(EXTRA_LAUNCH_CARRIER_APP, LAUNCH_APP_ACTIVATE);
// startActivityForResult(carrierAppIntent,REQUEST_CHECK_WFC_EMERGENCY_ADDRESS);                 

第六步：handleEntitlementStatusForActivation() - 处理激活流程结果
@MainThread
private void handleEntitlementStatusForActivation(EntitlementResult result) {
    Ts43VowifiStatus vowifiStatus = result.getVowifiStatus();
    if (vowifiStatus.vowifiEntitled()) {
        finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__SUCCESSFUL);
        mActivationUi.setResultAndFinish(Activity.RESULT_OK);
    } else {
        if (vowifiStatus.serverDataMissing()) {
            if (!TextUtils.isEmpty(result.getTermsAndConditionsWebUrl())) {
                mActivationUi.showWebview(
                        result.getTermsAndConditionsWebUrl(), /* postData= */ null);
            } else {
                mActivationUi.showWebview(
                        result.getEmergencyAddressWebUrl(),
                        result.getEmergencyAddressWebData());
            }
        } else if (vowifiStatus.incompatible()) {
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__INCOMPATIBLE);
            showErrorUi(R.string.failure_contact_carrier);
        } else {
            Log.e(TAG, "Unexpected status. Show error UI.");
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__UNEXPECTED_RESULT);
            showGeneralErrorUi();
        }
    }
}
/*
vowifiEntitled()：已激活，记录成功并返回 RESULT_OK
serverDataMissing()：需要注册
    public boolean serverDataMissing() {
        return entitlementStatus() == EntitlementStatus.DISABLED
                && (tcStatus() == TcStatus.NOT_AVAILABLE

| addrStatus() == AddrStatus.NOT_AVAILABLE);

    }
优先显示条款页面（如有 TermsAndConditionsWebUrl）
否则显示 E911 地址页面（EmergencyAddressWebUrl + EmergencyAddressWebData）
incompatible()：不兼容，显示错误提示
其他：显示通用错误
*/

```

### 更新E911地址:mActivationUi.showWebview() 

```java
流程图:
用户触发E911地址更新
    ↓
handleEntitlementStatusForUpdating() 或 handleEntitlementStatusForActivation()
    ↓
mActivationUi.showWebview(url, postData)
    ↓
WfcActivationActivity.showWebview()
    ├─ 主线程执行
    ├─ 创建 WfcWebPortalFragment.newInstance(url, postData)
    └─ FragmentTransaction.replace() 显示WebView
    ↓
WfcWebPortalFragment.onCreateView()
    ├─ 加载布局 fragment_webview
    ├─ 获取URL和POST数据
    ├─ 配置WebViewClient (显示/隐藏进度条)
    ├─ 添加视图监听器 (处理取消)
    ├─ 添加JavaScript接口: VoWiFiWebServiceFlow
    ├─ 启用JavaScript和DOM Storage
    └─ 加载URL:
        ├─ 有POST数据: postUrl(url, postData.getBytes())
        └─ 无POST数据: loadUrl(url)
    ↓
WebView显示运营商E911地址注册页面
    ↓
用户在网页中填写/更新地址信息并提交
    ↓
运营商服务器处理地址更新
    ↓
网页调用JavaScript回调:
    ├─ entitlementChanged() - 成功完成
    │   ├─ 设置 mFinishFlow = true
    │   └─ 调用 finishFlow()
    │       ├─ 显示等待UI
    │       └─ reevaluateEntitlementStatus()
    │           └─ 重新查询服务器状态
    │               └─ handleEntitlementStatusAfterUpdating()
    │                   ├─ vowifiEntitled: 返回RESULT_OK
    │                   ├─ serverDataMissing: 关闭WFC并返回RESULT_OK
    │                   └─ 其他: 显示错误
    │
    └─ dismissFlow() - 用户取消
        └─ 直接返回 RESULT_CANCELED
    ↓
setResultAndFinish(resultCode)
    └─ 返回Settings
        
具体流程:       
第一步:开启webview更新e911地址,如果是不是激活流程而是更新流程handleEntitlementStatusForUpdating,在关键步骤上一致
mActivationUi.showWebview(
                        result.getEmergencyAddressWebUrl(),
                        result.getEmergencyAddressWebData());

    @Override
    public boolean showWebview(String url, String postData) {
        runOnUiThreadIfAlive(
                () -> {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    mWfcWebPortalFragment = WfcWebPortalFragment.newInstance(url, postData);
                    ft.replace(R.id.wfc_activation_container, mWfcWebPortalFragment);
                    // commit may be executed after activity's state is saved.
                    ft.commitAllowingStateLoss();
                });
        return true;
    }
//在主线程执行 UI 操作
//创建 WfcWebPortalFragment 实例，传入 URL 和 POST 数据
//替换容器中的 Fragment，显示 WebView

第二步:WfcWebPortalFragment.newInstance() - 创建 Fragment
public static WfcWebPortalFragment newInstance(String url, String postData) {
    WfcWebPortalFragment frag = new WfcWebPortalFragment();

    Bundle args = new Bundle();
    args.putString(KEY_URL_STRING, url);
    args.putString(KEY_POST_DATA_STRING, postData);
    frag.setArguments(args);

    return frag;
}
//创建 Fragment 实例
//将 URL 和 POST 数据存入 Bundle

第三步：WfcWebPortalFragment.onCreateView() - 初始化 WebView
@Override
public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_webview, container, false);

    Bundle arguments = getArguments();
    Log.d(TAG, "Webview arguments: " + arguments);
    String url = arguments.getString(KEY_URL_STRING, "");
    String postData = arguments.getString(KEY_POST_DATA_STRING, "");

    ProgressBar spinner = v.findViewById(R.id.loadingbar);
    mWebView = v.findViewById(R.id.webview);
    mWebView.setWebViewClient(
            new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false; // Let WebView handle redirected URL
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    spinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    spinner.setVisibility(View.GONE);
                    super.onPageFinished(view, url);
                }
            });
    mWebView.addOnAttachStateChangeListener(
            new OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    if (!v.hasFocus()) {
                        v.requestFocus();
                    }
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    Log.d(TAG, "#onViewDetachedFromWindow");
                    if (!mFinishFlow) {
                        ((WfcActivationUi) getActivity()).setResultAndFinish(
                                Activity.RESULT_CANCELED);
                    }
                }
            });
    mWebView.addJavascriptInterface(new JsInterface(getActivity()), JS_CONTROLLER_NAME);
    WebSettings settings = mWebView.getSettings();
    settings.setDomStorageEnabled(true);
    settings.setJavaScriptEnabled(true);

    if (TextUtils.isEmpty(postData)) {
        mWebView.loadUrl(url);
    } else {
        mWebView.postUrl(url, postData.getBytes());
    }
    return v;
}
/*
1 加载布局和获取参数
加载 fragment_webview 布局
从 Bundle 获取 URL 和 POST 数据
2 配置 WebViewClient
shouldOverrideUrlLoading()：返回 false，允许 WebView 处理重定向
onPageStarted()：显示加载进度条
onPageFinished()：隐藏加载进度条
3 添加视图监听器
onViewAttachedToWindow()：请求焦点
onViewDetachedFromWindow()：如果未完成流程，返回 RESULT_CANCELED

4 添加 JavaScript 接口
mWebView.addJavascriptInterface(new JsInterface(getActivity()), JS_CONTROLLER_NAME);
给WebView注册一个JavaScript接口。JsInterface(getActivity())是一个Java对象实例，这个对象的public带@JavascriptInterface的方法可以被网页里的JS代码调用。
JS_CONTROLLER_NAME是接口在JS端的名字，比如（目前项目中）叫VoWiFiWebServiceFlow。这样，网页里的JS脚本就能通过window.VoWiFiWebServiceFlow.xxx()直接调用原生方法。
接口名：VoWiFiWebServiceFlow（TS.43 规范）
允许网页调用 entitlementChanged() 和 dismissFlow()

5 配置 WebSettings
WebSettings settings = mWebView.getSettings();
settings.setDomStorageEnabled(true);
启用 JavaScript
启用 DOM Storage
6 加载 URL
if (TextUtils.isEmpty(postData)) {
    mWebView.loadUrl(url);
} else {
    mWebView.postUrl(url, postData.getBytes());
}
有 POST 数据：使用 postUrl() 发送 POST 请求
无 POST 数据：使用 loadUrl() 发送 GET 请求

*/

第四步：用户填写 E911 地址
用户在 WebView 中
1.如果正常操作,调用entitlementChanged()
查看地址表单
填写或更新地址信息
提交表单
2.其他所有原因取消了,调用dismissFlow()
        /**
         * Callback function when the VoWiFi service flow ends prematurely, either by user
         * action or due to a web sheet or network error.
         */
        @JavascriptInterface
        public void dismissFlow() {
            Log.d(TAG, "#dismissFlow");
            mUi.setResultAndFinish(Activity.RESULT_CANCELED);
        }
//直接返回 RESULT_CANCELED，不重新评估
    
第五步：正常完成结束流程时网页调用 JavaScript 回调JsInterface#entitlementChanged()
        /**
         * Callback function when the VoWiFi service flow ends properly between the device and the
         * VoWiFi portal web server.
         */
        @JavascriptInterface
        public void entitlementChanged() {
            Log.d(TAG, "#entitlementChanged");
            mFinishFlow = true;
            mMainExecutor.execute(() -> mUi.getController().finishFlow());
        }
//设置 mFinishFlow = true，避免 onViewDetachedFromWindow() 取消
//在主线程调用 finishFlow()，重新评估状态

第六步：finishFlow() - 重新评估状态
    /**
     * Indicates the controller to re-evaluate WFC entitlement status after activation flow finished
     * successfully (ie. not canceled) by user.
     */
    @MainThread
    public void finishFlow() {
        showGeneralWaitingUi();
        reevaluateEntitlementStatus();
    }
//显示等待 UI
//重新查询服务器状态

    /** Re-evaluate entitlement status after updating. */
    @MainThread
    public void reevaluateEntitlementStatus() {
        EntitlementUtils.entitlementCheck(
                mImsEntitlementApi,
                result -> mMainThreadHandler.post(
                        () -> handleReevaluationEntitlementStatus(result)));
    }
//再次调用 checkEntitlementStatus() 查询服务器

    @MainThread
    private void handleReevaluationEntitlementStatus(@Nullable EntitlementResult result) {
        Log.d(TAG, "Reevaluation entitlement result: " + result);
        if (result == null) { // Network issue
            showGeneralErrorUi();
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__FAILED);
            return;
        }
        if (isActivationFlow()) {
            handleEntitlementStatusAfterActivation(result);
        } else {
            handleEntitlementStatusAfterUpdating(result);
        }
    }
//再次根据isActivationFlow()值重新评估

第七步：处理重新评估结果
1.如果是不是激活流程,则是更新流程
    @MainThread
    private void handleEntitlementStatusAfterUpdating(EntitlementResult result) {
        Ts43VowifiStatus vowifiStatus = result.getVowifiStatus();
        if (vowifiStatus.vowifiEntitled()) {
            mActivationUi.setResultAndFinish(Activity.RESULT_OK);
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__SUCCESSFUL);
        } else if (vowifiStatus.serverDataMissing()) {
            // Some carrier allows de-activating in updating flow.
            mImsUtils.turnOffWfc(
                    () -> {
                        finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__DISABLED);
                        mActivationUi.setResultAndFinish(Activity.RESULT_OK);
                    });
        } else {
            Log.e(TAG, "Unexpected status. Show error UI.");
            showGeneralErrorUi();
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__UNEXPECTED_RESULT);
        }
    }
//vowifiEntitled()：返回 RESULT_OK
//serverDataMissing()：关闭 WFC 并返回 RESULT_OK（部分运营商允许在更新流程中停用）
//否则提示出错

2.如果是激活流程（带重试）
    @MainThread
    private void handleEntitlementStatusAfterActivation(EntitlementResult result) {
        Ts43VowifiStatus vowifiStatus = result.getVowifiStatus();
        if (vowifiStatus.vowifiEntitled()) {
            mActivationUi.setResultAndFinish(Activity.RESULT_OK);
            finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__SUCCESSFUL);
        } else {
            if (vowifiStatus.serverDataMissing()) {
                // Check again after 5s, max retry 6 times
                if (mEvaluateTimes < ENTITLEMENT_STATUS_UPDATE_RETRY_MAX) {
                    mEvaluateTimes += 1;
                    mMainThreadHandler.postDelayed(
                            this::reevaluateEntitlementStatus,
                            getEntitlementStatusUpdateRetryIntervalMs());
                } else {
                    mEvaluateTimes = 0;
                    showGeneralErrorUi();
                    finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__TIMEOUT);
                }
            } else {
                // These should never happen, but nothing else we can do. Show general error.
                Log.e(TAG, "Unexpected status. Show error UI.");
                showGeneralErrorUi();
                finishStatsLog(IMS_SERVICE_ENTITLEMENT_UPDATED__APP_RESULT__UNEXPECTED_RESULT);
            }
        }
    }
//vowifiEntitled()：返回 RESULT_OK
//serverDataMissing()：最多重试 6 次，每次延迟 5 秒
//超时或失败：显示错误

第八步:设置结果码为RESULT_OK,关闭激活activity,回到 Settings
   @Override
    public void setResultAndFinish(int resultCode) {
        Log.d(TAG, "setResultAndFinish: result=" + resultCode);
        if (!isFinishing() && !isDestroyed()) {
            setResult(resultCode);
            finish();
        }
    }
//设置结果码并结束 Activity，返回 Settings
```

### EntitlementCheck结束:回到Settings,准备开启WFC和更新UI

```java
第一步：Settings 接收结果 - onActivityResult()
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "WFC activity request = " + requestCode + " result = " + resultCode);
        switch (requestCode) {
            case REQUEST_CHECK_WFC_EMERGENCY_ADDRESS:
                if (resultCode == Activity.RESULT_OK) {
                    updateWfcMode(true);
                }
                break;
//处理 RESULT_OK：E911 地址更新完成：直接调用 updateWfcMode(true)

第二步：updateWfcMode(true) - 开启 WFC
    /*
     * Turn on/off WFC mode with ImsManager and update UI accordingly
     */
    private void updateWfcMode(boolean wfcEnabled) {
        Log.i(TAG, "updateWfcMode(" + wfcEnabled + ")");
        boolean hasException = false;
        try {
            mImsMmTelManager.setVoWiFiSettingEnabled(wfcEnabled);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "updateWfcMode: Exception", e);
            hasException = true;
        }

        int wfcMode = ImsMmTelManager.WIFI_MODE_UNKNOWN;
        int wfcRoamingMode = ImsMmTelManager.WIFI_MODE_UNKNOWN;
        if (!hasException) {
            try {
                wfcMode = mImsMmTelManager.getVoWiFiModeSetting();
                wfcRoamingMode = mImsMmTelManager.getVoWiFiRoamingModeSetting();
            } catch (IllegalArgumentException e) {
                hasException = true;
                Log.e(TAG, "updateWfcMode: Exception", e);
            }
        }
        updateButtonWfcMode(wfcEnabled && !hasException, wfcMode, wfcRoamingMode);
        if (wfcEnabled) {
            mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), wfcMode);
        } else {
            mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), -1);
        }
    }
//1. mImsMmTelManager.setVoWiFiSettingEnabled(wfcEnabled):写入系统设置，通知 IMS 框架开启 WFC
//2.读取 WFC 模式和漫游模式（用于更新 UI）
//3.调用 updateButtonWfcMode() 更新 UI 状态
//4.记录 Metrics
```

### 开启WFC:IMS 更新 WFC相关能力

```java
 
mImsMmTelManager.setVoWiFiSettingEnabled(wfcEnabled);
//mImsMmTelManager 是通过 ImsMmTelManager.createForSubscriptionId(subId) 创建的实例
//传入 true 表示开启 WFC

第一步：ImsMmTelManager 实现 - 通过 Binder 调用 Telephony Service
    /**
     * Sets the user's setting for whether or not Voice over WiFi is enabled.
     *
     * @throws IllegalArgumentException if the subscription associated with this operation is not
     * active (SIM is not inserted, ESIM inactive) or invalid.
     * @throws UnsupportedOperationException If the device does not have
     *          {@link PackageManager#FEATURE_TELEPHONY_IMS}.
     * @param isEnabled true if the user's setting for Voice over WiFi is enabled, false otherwise=
     * @see #isVoWiFiSettingEnabled()
     * @hide
     */
    @SystemApi
    @RequiresPermission(Manifest.permission.MODIFY_PHONE_STATE)
    public void setVoWiFiSettingEnabled(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            throw new RuntimeException("Could not find Telephony Service.");
        }

        try {
            iTelephony.setVoWiFiSettingEnabled(mSubId, isEnabled);
        } catch (ServiceSpecificException e) {
            if (e.errorCode == ImsException.CODE_ERROR_INVALID_SUBSCRIPTION) {
                // Rethrow as runtime error to keep API compatible.
                throw new IllegalArgumentException(e.getMessage());
            } else {
                throw new RuntimeException(e.getMessage());
            }
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }
/*
getITelephony() 获取 Telephony Service 的 Binder 接口
通过 ITelephony.setVoWiFiSettingEnabled(mSubId, isEnabled) 跨进程调用
异常处理：
	1.ServiceSpecificException：无效订阅时抛出 IllegalArgumentException
	2.RemoteException：Binder 调用失败时抛出 RuntimeException
*/
第二步：PhoneInterfaceManager.setVoWiFiSettingEnabled - 权限检查和路由
    @Override
    public void setVoWiFiSettingEnabled(int subId, boolean isEnabled) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(mApp, subId,
                "setVoWiFiSettingEnabled");

        enforceTelephonyFeatureWithException(getCurrentPackageName(),
                FEATURE_TELEPHONY_IMS, "setVoWiFiSettingEnabled");

        final long identity = Binder.clearCallingIdentity();
        try {
            int slotId = getSlotIndexOrException(subId);
            // This setting doesn't require an active ImsService connection, so do not verify. The
            // new setting will be picked up when the ImsService comes up next if it isn't up.
            ImsManager.getInstance(mApp, slotId).setWfcSetting(isEnabled);
        } catch (ImsException e) {
            throw new ServiceSpecificException(e.getCode());
        } finally {
            Binder.restoreCallingIdentity(identity);
        }
    }
//1.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege()检查调用者是否有 MODIFY_PHONE_STATE 权限或运营商权限,无权限时抛出 SecurityException
//2.enforceTelephonyFeatureWithException()检查设备是否支持 IMS 功能,不支持时抛出异常
//3.Binder.clearCallingIdentity()：清除调用者身份，以系统身份执行
//4.getSlotIndexOrException(subId)：将 subId 转换为 slotId
//5.ImsManager.getInstance(mApp, slotId).setWfcSetting(isEnabled)：调用 ImsManager 设置 WFC
//6.Binder.restoreCallingIdentity(identity)：恢复调用者身份

第四步：ImsManager.setWfcSetting - 写入设置数据库并更新对应能力
    /**
     * Change persistent WFC enabled setting for slot.
     */
    public void setWfcSetting(boolean enabled) {
        if (enabled && !isWfcProvisionedOnDevice()) {
            log("setWfcSetting: Not possible to enable WFC due to provisioning.");
            return;
        }
        int subId = getSubId();
        if (!isSubIdValid(subId)) {
            loge("setWfcSetting: invalid sub id, can not set WFC setting in siminfo db; subId="
                    + subId);
            return;
        }
        mSubscriptionManagerProxy.setSubscriptionProperty(subId,
                SubscriptionManager.WFC_IMS_ENABLED, booleanToPropertyString(enabled));

        try {
            if (enabled) {
                boolean isNonTtyWifi = isNonTtyOrTtyOnVoWifiEnabled();
                CapabilityChangeRequest request = new CapabilityChangeRequest();
                updateVoiceWifiFeatureAndProvisionedValues(request, isNonTtyWifi);
                changeMmTelCapability(request);
                // Ensure IMS is on if this setting is updated.
                turnOnIms();
            } else {
                // This may cause IMS to be disabled, re-evaluate all caps
                reevaluateCapabilities();
            }
        } catch (ImsException e) {
            loge("setWfcSetting: " + e);
        }
    }
/*
1.如果开启但未 Provisioned，直接返回，不写入设置.isWfcProvisionedOnDevice() 检查：
	1.如果 KEY_CARRIER_VOLTE_OVERRIDE_WFC_PROVISIONING_BOOL 为 true，需要 VoLTE 已 Provisioned
	2.如果需要 IMS Provisioning（isMmTelProvisioningRequired），检查 WFC 是否已 Provisioned
	3.否则直接返回 true
2.SubId 验证
3.写入 SubscriptionManager 数据库
	1. SubscriptionManager.setSubscriptionProperty()
		参数
			1.subId：订阅 ID
			2.propKey：SubscriptionManager.WFC_IMS_ENABLED（常量字符串）
			3.propValue："1"（开启）或 "0"（关闭）
		作用:写入 siminfo 表;触发 ContentObserver,通知已注册的监听器订阅属性已变更
            1.表名：siminfo
            2.列名：wfc_ims_enabled
            3.值：1（开启）或 0（关闭）
            4.数据库位置：/data/data/com.android.providers.telephony/databases/telephony.db
4.更新 IMS 能力（开启时）
	1.检查是否为非 TTY 模式，或 TTY 模式下是否支持 VoWiFi,如果 TTY 且不支持 VoWiFi，后续不会启用 WFC
	2.创建 CapabilityChangeRequest，用于批量更新 IMS 能力
	3.updateVoiceWifiFeatureAndProvisionedValues()更新 VoWiFi 能力和 Provisioning 值
	4.changeMmTelCapability()应用能力变更
	5.turnOnIms() 确保 IMS 服务开启，以便后续注册
5.reevaluateCapabilities()重新评估能力（关闭时）
*/

第五步：updateVoiceWifiFeatureAndProvisionedValues() - 更新 VoWiFi 能力和 Provisioning 值
    /**
     * Update WFC config
     */
    private void updateVoiceWifiFeatureAndProvisionedValues(CapabilityChangeRequest request,
     boolean isNonTty) {
        boolean isNetworkRoaming =  false;
        if (mTelephonyManager == null) {
            loge("updateVoiceWifiFeatureAndProvisionedValues: TelephonyManager is null, assuming"
                    + " not roaming.");
        } else {
            TelephonyManager tm = mTelephonyManager.createForSubscriptionId(getSubId());
            isNetworkRoaming = tm.isNetworkRoaming();
        }

        boolean available = isWfcEnabledByPlatform();
        boolean enabled = isWfcEnabledByUser();
        boolean isProvisioned = isWfcProvisionedOnDevice();
        int mode = getWfcMode(isNetworkRoaming);
        boolean roaming = isWfcRoamingEnabledByUser();
        boolean isFeatureOn = available && enabled && isProvisioned;

        log("updateWfcFeatureAndProvisionedValues: available = " + available
                + ", enabled = " + enabled
                + ", mode = " + mode
                + ", isNetworkRoaming = " + isNetworkRoaming
                + ", provisioned = " + isProvisioned
                + ", roaming = " + roaming
                + ", isFeatureOn = " + isFeatureOn
                + ", isNonTtyWifi = " + isNonTty);

        if (isFeatureOn && isNonTty) {
            request.addCapabilitiesToEnableForTech(
                    MmTelFeature.MmTelCapabilities.CAPABILITY_TYPE_VOICE,
                    ImsRegistrationImplBase.REGISTRATION_TECH_IWLAN);
        } else {
            request.addCapabilitiesToDisableForTech(
                    MmTelFeature.MmTelCapabilities.CAPABILITY_TYPE_VOICE,
                    ImsRegistrationImplBase.REGISTRATION_TECH_IWLAN);
        }

        if (!isFeatureOn) {
            mode = ImsMmTelManager.WIFI_MODE_CELLULAR_PREFERRED;
            roaming = false;
        }
        setWfcModeInternal(mode);
        setWfcRoamingSettingInternal(roaming);
    }
/*
1.检查漫游状态
	通过 TelephonyManager.isNetworkRoaming() 判断
2.检查 WFC 状态
	1.isWfcEnabledByPlatform()：平台是否支持
	2.isWfcEnabledByUser()：用户是否开启（从 SubscriptionManager 读取）
	3.isWfcProvisionedOnDevice()：是否已 Provisioned
	4.isFeatureOn = available && enabled && isProvisioned
3.更新能力请求
	1.如果 isFeatureOn && isNonTty：添加 CAPABILITY_TYPE_VOICE + REGISTRATION_TECH_IWLAN 到启用列表
	2.否则：添加到禁用列表
4.更新 WFC 模式
	1.如果未开启：设置为 WIFI_MODE_CELLULAR_PREFERRED，禁用漫游
	2.setWfcModeInternal() 
		1.wfcMode：WFC 模式值
            1.WIFI_MODE_WIFI_ONLY (0)：仅 Wi‑Fi
            2.WIFI_MODE_CELLULAR_PREFERRED (1)：移动数据优先
            3.WIFI_MODE_WIFI_PREFERRED (2)：Wi‑Fi 优先
            4.WIFI_MODE_UNKNOWN (-1)：未知
         2.getConfigInterface().setConfig(ProvisioningManager.KEY_VOICE_OVER_WIFI_MODE_OVERRIDE, value);
         	创建 ImsConfig 包装类,IMS 服务（如 ImsConfigImpl）的 setConfig() 会
         		1.通过 IMS Radio HAL 发送到 Modem
         		2.Modem 更新 NV（Non-Volatile）配置
	3.setWfcRoamingSettingInternal() 设置roaming
		与setWfcModeInternal类似:getConfigInterface().setConfig(ProvisioningManager.KEY_VOICE_OVER_WIFI_ROAMING_ENABLED_OVERRIDE, value);
*/

第六步：changeMmTelCapability() - 应用能力变更
    private void changeMmTelCapability(CapabilityChangeRequest r) throws ImsException {
        MmTelFeatureConnection c = getOrThrowExceptionIfServiceUnavailable();
        try {
            logi("changeMmTelCapability: changing capabilities for sub: " + getSubId()
                    + ", request: " + r);
            c.changeEnabledCapabilities(r, null);
            ImsStatsCallback cb = getStatsCallback(mPhoneId);
            if (cb == null) {
                return;
            }
            for (CapabilityChangeRequest.CapabilityPair enabledCaps : r.getCapabilitiesToEnable()) {
                cb.onEnabledMmTelCapabilitiesChanged(enabledCaps.getCapability(),
                        enabledCaps.getRadioTech(), true);
            }
            for (CapabilityChangeRequest.CapabilityPair disabledCaps :
                    r.getCapabilitiesToDisable()) {
                cb.onEnabledMmTelCapabilitiesChanged(disabledCaps.getCapability(),
                        disabledCaps.getRadioTech(), false);
            }
        } catch (RemoteException e) {
            throw new ImsException("changeMmTelCapability(CCR)", e,
                    ImsReasonInfo.CODE_LOCAL_IMS_SERVICE_DOWN);
        }
    }
/*
1.获取 MmTelFeatureConnection:getOrThrowExceptionIfServiceUnavailable()
2.调用 MmTelFeatureConnection.changeEnabledCapabilities():IMS 服务处理能力变更请求
3.通知统计系统能力变更
	1.通知启用的能力变更:遍历要启用的能力对,调用 onEnabledMmTelCapabilitiesChanged(capability, radioTech, true)
	2.通知禁用的能力变更:遍历要禁用的能力对,调用 onEnabledMmTelCapabilitiesChanged(capability, radioTech, false)
*/

第六步：MmTelFeatureConnection.changeEnabledCapabilities() - IMS 服务处理能力变更请求

//通过这种调用,将 Framework 与 IMS 服务解耦
MmTelFeatureConnection.changeEnabledCapabilities()
    ↓ (Binder 调用)
IImsMmTelFeature.changeCapabilitiesConfiguration()
    ↓
MmTelFeature.IImsMmTelFeature.Stub.changeCapabilitiesConfiguration()
    ↓
MmTelFeature.requestChangeEnabledCapabilities()
    ↓
ImsFeature.requestChangeEnabledCapabilities()
    ↓
MmTelFeature.changeEnabledCapabilities() (抽象方法，由子类实现)
    ↓
ImsServiceSub.changeEnabledCapabilities() (具体实现)

第七步：ImsServiceSub.changeEnabledCapabilities() (具体实现) - IMS 服务处理能力变更请求
    /**
     * The MmTelFeature should override this method to handle the enabling/disabling of
     * MmTel Features, defined in {@link MmTelCapabilities.MmTelCapability}. The framework assumes
     * the {@link CapabilityChangeRequest} was processed successfully. If a subset of capabilities
     * could not be set to their new values,
     * {@link CapabilityCallbackProxy#onChangeCapabilityConfigurationError} must be called
     * individually for each capability whose processing resulted in an error.
     *
     * Enabling/Disabling a capability here indicates that the capability should be registered or
     * deregistered (depending on the capability change) and become available or unavailable to
     * the framework.
     *
     * This method invokes ImsConfigImpl's setFeatureValue method for each CapabilityPair in the
     * request, and keeps a track of the status of each request via the corresponding
     * SetCapabilityListener instance.
     *
     * NOTE: This IMS Service implementation does not fully support the scenario of back to back
     *       calls to this method. To support such functionality, this class would need to
     *       implement a CapabilityCallbackProxy token system, where each SetCapabilityListener
     *       instance would have to get the appropriate CapabilityCallbackProxy instance to call
     *       its callbacks on.
     */
    @Override
    public void changeEnabledCapabilities(CapabilityChangeRequest request,
            CapabilityCallbackProxy c) {
        if (request == null || c == null) {
            loge("changeEnabledCapabilities :: Invalid argument(s).");
            return;
        }

        List<CapabilityPair> capsToEnable = request.getCapabilitiesToEnable();
        List<CapabilityPair> capsToDisable = request.getCapabilitiesToDisable();
        if (capsToEnable.isEmpty() && capsToDisable.isEmpty()) {
            loge("changeEnabledCapabilities :: No CapabilityPair objects to process!");
            return;
        }

        mCapabilityCallback = c;

        ArrayList<CapabilityStatus> capabilityStatusList = new ArrayList<>();
        for (CapabilityPair cp : capsToEnable) {
            if (!canChangeCapability(cp)) {
                loge("changeEnabledCapabilities :: ignoring unsupported enable capability request: "
                        + cp);
                mCapabilityCallback.onChangeCapabilityConfigurationError(cp.getCapability(),
                        cp.getRadioTech(), ImsFeature.CAPABILITY_ERROR_GENERIC);
                continue;
            }
            CapabilityStatus cs = new CapabilityStatus(cp.getCapability(), cp.getRadioTech(),
                    ProvisioningManager.PROVISIONING_VALUE_ENABLED);
            capabilityStatusList.add(cs);
        }

        for (CapabilityPair cp : capsToDisable) {
            if (!canChangeCapability(cp)) {
                loge("changeEnabledCapabilities :: ignoring unsupported disable capability " +
                        "request: " + cp);
                mCapabilityCallback.onChangeCapabilityConfigurationError(cp.getCapability(),
                        cp.getRadioTech(), ImsFeature.CAPABILITY_ERROR_GENERIC);
                continue;
            }
            CapabilityStatus cs = new CapabilityStatus(cp.getCapability(), cp.getRadioTech(),
                    ProvisioningManager.PROVISIONING_VALUE_DISABLED);
            capabilityStatusList.add(cs);

        }

        mImsConfigImpl.setCapabilityValue(capabilityStatusList,
                new SetCapabilityListener());
    }
/*
1.参数校验和提取能力列表
2.遍历要启用的能力：
	1.CapabilityPair 结构
		1.capability：能力类型（如 CAPABILITY_TYPE_VOICE）
		2.radioTech：无线技术（如 REGISTRATION_TECH_IWLAN、REGISTRATION_TECH_LTE）
    2.检查是否可变更（canChangeCapability()）
    3.创建 CapabilityStatus，状态为 PROVISIONING_VALUE_ENABLED
    4.加入 capabilityStatusList
3.遍历要禁用的能力：
    1.检查是否可变更
    2.创建 CapabilityStatus，状态为 PROVISIONING_VALUE_DISABLED
    3.加入 capabilityStatusList
4.调用 ImsConfigImpl.setCapabilityValue() 设置能力值
*/

第八步：ImsConfigImpl.setCapabilityValue() - 发送命令到modem
    public void setCapabilityValue(ArrayList<CapabilityStatus> capabilityStatusList,
            SetCapabilityValueListener listener) {
        mContext.enforceCallingOrSelfPermission(MODIFY_PHONE_STATE, "setCapabilityValue");
        mCi.setServiceStatus(mHandler.obtainMessage(EVENT_SET_FEATURE_VALUE, listener),
                capabilityStatusList, CallDetails.CALL_RESTRICT_CAUSE_NONE);
    }
//权限检查：MODIFY_PHONE_STATE
//通过 mCi.setServiceStatus() 发送到 Modem
//使用 SetCapabilityValueListener 监听结果

第九步:SetCapabilityListener打印和处理设置Capability结果
    private class SetCapabilityListener implements ImsConfigImpl.SetCapabilityValueListener {
        @Override
        public void onSetCapabilityValueSuccess(int capability, int radioTech, int value) {
            logd( "onSetCapabilityValueSuccess :: capability=" + capability
                    + " radioTech=" + radioTech + " value=" + value);
            // NOTE: Framework expects IMS Service to only report failures, so we don't
            // report anything at this point.
        }

        @Override
        public void onSetCapabilityValueFailure(int capability, int radioTech,
                ImsConfigImpl.SetCapabilityFailCause reason) {
            logd( "onSetCapabilityValueFailure :: capability=" + capability
                    + " radioTech=" + radioTech + " reason=" + reason);
            if (mCapabilityCallback == null) {
                loge("onSetCapabilityValueFailure :: Null mCapabilityCallback!");
                return;
            }
            mCapabilityCallback.onChangeCapabilityConfigurationError(capability, radioTech,
                    getSetCapabilityFailError(reason));
        }
    }
```

### WFC流程结束:更新 UI 状态,结束WFC流程

```java
第一步:调用 updateButtonWfcMode() - 更新 UI 状态
	private void updateButtonWfcMode(boolean wfcEnabled,
            int wfcMode, int wfcRoamingMode) {
        mButtonWfcMode.setSummary(getWfcModeSummary(wfcMode));
        mButtonWfcMode.setEnabled(wfcEnabled && mEditableWfcMode);
        // mButtonWfcRoamingMode.setSummary is not needed; summary is just selected value.
        mButtonWfcRoamingMode.setEnabled(wfcEnabled && mEditableWfcRoamingMode
                && !overrideWfcRoamingModeWhileUsingNtn());

        final PreferenceScreen preferenceScreen = getPreferenceScreen();
        final boolean updateAddressEnabled = (getCarrierActivityIntent() != null);
        if (wfcEnabled) {
            // Don't show WFC (home) preference if it's not editable.
            mButtonWfcMode.setVisible(mEditableWfcMode);
            // Don't show WFC roaming preference if it's not editable.
            mButtonWfcRoamingMode.setVisible(
                    mEditableWfcRoamingMode && !mUseWfcHomeModeForRoaming);
            mUpdateAddress.setVisible(updateAddressEnabled);
        } else {
            mButtonWfcMode.setVisible(false);
            mButtonWfcRoamingMode.setVisible(false);
            mUpdateAddress.setVisible(false);
        }
        updateDescriptionForOptions(
                List.of(mButtonWfcMode, mButtonWfcRoamingMode, mUpdateAddress));
    }
/*
1.更新 WFC 模式按钮（mButtonWfcMode）
	1.设置摘要文本,根据 wfcMode 选择对应的字符串资源：
		1.WIFI_MODE_WIFI_ONLY：仅 Wi‑Fi
		2.WIFI_MODE_CELLULAR_PREFERRED：移动数据优先
		3.WIFI_MODE_WIFI_PREFERRED：Wi‑Fi 优先
		4.IMS_PREFERRED：IMS 优先
	2.设置启用状态
        1.启用条件：wfcEnabled && mEditableWfcMode
        2.mEditableWfcMode 来自 CarrierConfig：KEY_EDITABLE_WFC_MODE_BOOL
2.更新 WFC 漫游模式按钮（mButtonWfcRoamingMode）
	设置启用状态
		1.启用条件：wfcEnabled && mEditableWfcRoamingMode && !overrideWfcRoamingModeWhileUsingNtn()
        2.mEditableWfcRoamingMode 来自 CarrierConfig：KEY_EDITABLE_WFC_ROAMING_MODE_BOOL
        3.overrideWfcRoamingModeWhileUsingNtn()：使用 NTN（非地面网络）时禁用
3.WFC 启用时的可见性
    1.mButtonWfcMode：仅在 mEditableWfcMode 为 true 时显示
    2.mButtonWfcRoamingMode：仅在 mEditableWfcRoamingMode 为 true 且 !mUseWfcHomeModeForRoaming 时显示
    3.mUpdateAddress：仅在 updateAddressEnabled 为 true 时显示（存在运营商 E911 地址管理 Activity）
4.WFC 禁用时的可见性:WFC 禁用时，隐藏所有相关 Preference
5.调用 updateDescriptionForOptions(),传入所有相关 Preference，用于判断是否有可见选项	 
*/

第二步:  调用updateDescriptionForOptions() - 判断preference是否带options
    private void updateDescriptionForOptions(List<Preference> visibleOptions) {
        LinkifyDescriptionPreference pref = findPreference(PREFERENCE_NO_OPTIONS_DESC);
        if (pref == null) {
            return;
        }

        boolean optionsAvailable = visibleOptions.stream().anyMatch(Preference::isVisible);
        if (!optionsAvailable) {
            final Resources res = getResourcesForSubId();
            String emptyViewText = res.getString(R.string.wifi_calling_off_explanation,
                    res.getString(R.string.wifi_calling_off_explanation_2));
            pref.setSummary(emptyViewText);
        }
        pref.setVisible(!optionsAvailable);
    }
//当WFC（Wi-Fi Calling）界面上没有任何可用选项（如子设置、切换等，用户啥也不能点）时，给用户显示一段说明文本；如果有可用选项，则隐藏这段说明。
/*
1.获得默认无选项时用来显示的preference:查找 PREFERENCE_NO_OPTIONS_DESC Preference
2.检查是否有可见选项：visibleOptions.stream().anyMatch(Preference::isVisible)
3.无可见选项时：
	1.设置说明文本（WFC 关闭说明）
	2.显示该 Preference
4.有可见选项时：
	隐藏该 Preference
*/
```

