(ns try-webdriver
  (:require [clj-http.lite.client :as client]
            [cheshire.core :refer :all])
  (:use [slingshot.slingshot :only [try+]]))

;; following https://w3c.github.io/webdriver/
;; and https://github.com/aerokube/selenium-openapi
;; start web driver manually
(defn server [uri] (str "http://localhost:9515" uri))

(def caps (generate-string
           {:capabilities
            {:alwaysMatch
             {:browserName "chrome"}}}))

;; New Session
(try+ (client/post (server "/session")
                   {:body caps})
      (catch Object {:keys [body]}
         (parse-string body true)))

;; Delete Session (manually put sessionIs from `New Session` response)
(try+ (client/delete (server "/session/6ca961a08a5a1d0b8eae6a9f7ba116c9"))
      (catch Object {:keys [body]}
        (parse-string body true)))



