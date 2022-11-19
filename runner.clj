#!/usr/bin/env bb

;https://book.babashka.org/#_running_tests

(require '[clojure.test :as t]
         '[babashka.classpath :as cp])

(cp/add-classpath "src:test")

(require 'test 'test-extra)

(def test-results
  (t/run-tests 'test 'test-extra))

(def failures-and-errors
  (let [{:keys [fail error]} test-results]
    (+ fail error)))

(System/exit failures-and-errors)