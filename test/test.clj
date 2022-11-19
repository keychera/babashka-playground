(ns test
  (:require [clojure.test :refer [deftest is testing]]
            [chera :refer [hello]]))

(deftest hello-works
  (testing "Are you polite?"
    (is (= (hello "me") "not true"))))

(deftest type-system-works
  (testing "Is type system working?"
    (is (instance? Integer 256))))