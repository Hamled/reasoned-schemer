(ns name.hamled.reasoned-schemer.ch1
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
  (:use [clojure.core.logic.pldb]))

;; Frame 1:10
(run* [q]
      u#)
;; => ()

;; Frame 1:11
(run* [q]
      (== true q))
;; => (true)

;; Frame 1:12
(run* [q]
      u#
      (== true q))
;; => ()

;; Frame 1:13
(run* [q]
      s#
      (== true q))
;; => (true)

;; Frame 1:15
(run* [r]
      s#
      (== 'corn r))
;; => (corn)

;; Frame 1:17
(run* [r]
      u#
      (== 'corn r))
;; => ()
