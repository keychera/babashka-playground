(ns try-httpkit 
  (:require [clojure.java.browse :as browse]
            [org.httpkit.server :refer [run-server]]))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(def port 4242)

(defn -main [& _]
 (let [url (str "http://localhost:" port "/")]
   (run-server app {:port port})
   (println "serving" url)
   @(promise))
  )

(when (= *file* (System/getProperty "babashka.file"))
  (apply -main *command-line-args*))
