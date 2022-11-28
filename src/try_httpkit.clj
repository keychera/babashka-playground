(ns try-httpkit 
  (:require [clojure.java.browse :as browse]
            [org.httpkit.server :refer [run-server]]))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(def port 4242)

(run-server app {:port port})

(println "Starting http server at" port)
(browse/browse-url (str "http://localhost:" port "/"))

@(promise)
