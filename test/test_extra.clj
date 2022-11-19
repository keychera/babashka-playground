(ns test-extra
  (:require [clojure.test :refer [deftest is testing]]))

(deftest math-works
  (testing "Is math working?"
    (is (= 5 (+ 2 2)))))
