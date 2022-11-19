(ns test 
  (:require [clojure.test :refer [deftest is testing]]))


(deftest type-system-works
  (testing "Is type system working?"
    (is (instance? Integer 256))))