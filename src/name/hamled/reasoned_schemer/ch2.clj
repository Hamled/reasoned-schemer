(ns name.hamled.reasoned-schemer.ch2
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
  (:use [clojure.core.logic.pldb]))

;; Frame 2:1
(let [x (fn [a] a)
      y 'c]
  (x y))
;; => c

;; Frame 2:2
(run* [r]
      (fresh [x y]
             (== (llist x y) r)))
;; => ((_0 . _1))

;; Frame 2:3
(run* [r]
      (fresh [v w]
             (== (let [[x y] [v w]]
                   (llist x y)) r)))
;; => ((_0 . _1))

;; Frame 2:4
(first '(grape rasin pear))
;; => grape
