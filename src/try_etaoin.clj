(ns try-etaoin)

(require '[etaoin.api :as e]
         '[etaoin.keys :as k])

; manually install WebDriver first
(def driver (e/chrome {:driver-log-level "INFO"}))

(defn -main [& _]
  (doto driver
    (e/go "https://en.wikipedia.org/")
    (e/wait-visible [{:id :simpleSearch} {:tag :input :name :search}])
    (e/fill {:tag :input :name :search} "Clojure programming language")
    (e/fill {:tag :input :name :search} k/enter)
    (e/wait-visible {:class :mw-search-results})
    (e/click [{:class :mw-search-results} {:class :mw-search-result-heading} {:tag :a}])
    (e/wait-visible {:id :firstHeading})
    (e/quit)))

