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

;; Frame 1:18
(run* [q]
      s#
      (== false q))
;; => (false)

;; Frame 1:22
(run* [x]
      (let [x false]
        (== true x)))
;; => ()

;; Frame 1:23
(run* [q]
      (fresh [x]
        (== true x)
        (== true q)))
;; => (true)

;; Frame 1:26
(run* [q]
      (fresh [x]
        (== x true)
        (== true q)))
;; => (true)

;; Frame 1:27
(run* [q]
      (fresh [x]
        (== x true)
        (== q true)))
;; => (true)

;; Frame 1:28
(run* [x]
      s#)
;; => (_0)

;; Frame 1:29
(run* [x]
      (let [x false]
        (fresh [x]
               (== true x))))
;; => (_0)

;; Frame 1:30
(run* [r]
      (fresh [x y]
            (== (llist x y) r)))
;; => ((_0 . _1))

;; Frame 1:31
(run* [s]
      (fresh [t u]
            (== (llist t u) s)))
;; => ((_0 . _1))
