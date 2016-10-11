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

;; Frame 2:5
(first '(a c o r n))
;; => a

;; Frame 2:6
(run* [r]
      (firsto (llist 'a 'c 'o 'r 'n) r))
;; => (a)

;; Frame 2:7
(run* [q]
      (firsto (llist 'a 'c 'o 'r 'n) 'a)
      (== true q))
;; => (true)

;; Frame 2:8
(run* [r]
      (fresh [x y]
             (firsto (llist r y) x)
             (== 'pear x)))
;; => (pear)

;; Frame 2:11
(run* [r]
      (fresh [x y]
             (firsto (llist 'grape 'raisin 'pear) x)
             (firsto (llist '(a) '(b) '(c)) y)
             (== (llist x y) r)))
;; => ((grape a))

;; Frame 2:13
(rest '(grape raisin pear))
;; => (raisin pear)

;; Frame 2:14
(first (rest '(a c o r n)))
;; => c

;; Frame 2:15
(run* [r]
      (fresh [v]
             (resto (llist 'a 'c 'o 'r 'n) v)
             (firsto v r)))
;; => (c)

;; Frame 2:17
(llist
  (rest '(grape raisin pear))
  (first '((a) (b) (c))))
;; => ((raisin pear) a)

;; Frame 2:18
(run* [r]
      (fresh [x y]
             (resto (llist 'grape 'raisin 'pear) x)
             (firsto (llist '(a) '(b) '(c)) y)
             (== (llist x y) r)))
;; => (((raisin . pear) a))
