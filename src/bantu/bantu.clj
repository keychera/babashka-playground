(ns bantu.bantu
  (:require [bantu.common :refer [from-here]]
            [clojure.core.match :as match]
            [clojure.string :as str]
            [org.httpkit.server :refer [run-server]]))

(def home (slurp (from-here "home.html")))

(def port 4242)
(def url (str "http://localhost:" port "/"))

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

;; https://http-kit.github.io/server.html#stop-server
(defonce server (atom nil))

(defn stop-bantuin []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn start-bantuin []
  (reset! server (run-server #'router {:port port})))


