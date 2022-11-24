(ns try-appium
  (:require [clj-http.lite.client :as client]
            [cheshire.core :refer :all])
  (:use [slingshot.slingshot :only [try+]]))

;; following https://appium.io/docs/en/commands/session/create/
;; and other relevants docs in appium.io 
;; start appium manually
(defn server [uri] (str "http://localhost:4723/wd/hub" uri))

(def caps (generate-string
           {:desiredCapabilities
            {:appActivity "com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity"
             :appPackage "com.google.android.youtube"
             :udid "RR8N807K9ML"
             :automationName "UIAutomator2"
             :uiautomator2ServerInstallTimeout 20000
             :newCommandTimeout 300
             :adbExecTimeout 40000
             :device "SM_A515F"
             :platformName "Android"
             :autoGrantPermissions true}
            :capabilities
            {:firstMatch
             [{:appium:appPackage "com.google.android.youtube"
               :appium:appActivity "com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity"
               :appium:udid "RR8N807K9ML"
               :appium:automationName "UIAutomator2"
               :uiautomator2ServerInstallTimeout 20000
               :appium:newCommandTimeout 300
               :appium:adbExecTimeout 40000
               :device "SM_A515F"
               :platformName "android"
               :appium:autoGrantPermissions false}]}}))

;; New Session
(try+ (client/post (server "/session")
                   {:body caps
                    :content-type :json})
      (catch Object {:keys [body]}
        (parse-string body true)))

;; Delete Session (manually put sessionIs from `New Session` response)
(try+ (client/delete (server "/session/1b208a70-17a7-4af6-b662-8f2f9bc39fdd"))
      (catch Object {:keys [body]}
        (parse-string body true)))
