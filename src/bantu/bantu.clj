(ns bantu.bantu
  (:require [clojure.core.match :as match]
            [clojure.string :as str]
            [org.httpkit.server :refer [run-server]]))

(def f-sep (java.io.File/separator))
(defn from-here [filename]
  (str (as-> *file* it (str/split it (re-pattern f-sep)) (drop-last it) (str/join f-sep it)) f-sep filename))

(def home (slurp (from-here "home.html")))

(def port 4242)

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body home})

(defn clicked [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body "<h1>BOO!</h1>"})

;; https://gist.github.com/borkdude/1627f39d072ea05557a324faf5054cf3
(defn router [req]
  (let [paths (vec (rest (str/split (:uri req) #"/")))]
    (match/match [(:request-method req) paths]
      [:get []] (app req)
      [:post ["clicked"]] (clicked req)
      :else {:body "<p>Page not found.</p>"})))

(defn -main [& _]
  (let [url (str "http://localhost:" port "/")]
    (run-server router {:port port})
    (println "serving" url)
    @(promise)))

(when (= *file* (System/getProperty "babashka.file"))
  (apply -main *command-line-args*))