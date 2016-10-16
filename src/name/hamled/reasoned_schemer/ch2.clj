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

;; Frame 2:19
(run* [q]
      (resto (llist 'a 'c 'o 'r 'n) (llist 'c 'o 'r 'n))
      (== true q))
;; => (true)

;; Frame 2:20
(run* [x]
      (resto (llist 'c 'o 'r 'n) (llist x 'r 'n)))
;; => (o)

;; Frame 2:21
(run* [l]
      (fresh [x]
             (resto l (llist 'c 'o 'r 'n))
             (firsto l x)
             (== 'a x)))
;; => ((a c o r . n))

;; Frame 2:22
(run* [l]
      (conso (llist 'a 'b 'c) (llist 'd 'e) l))
;; => (((a b . c) d . e))

;; Frame 2:23
(run* [x]
      (conso x (llist 'a 'b 'c) (llist 'd 'a 'b 'c)))
;; => (d)

;; Frame 2:24
(run* [r]
      (fresh [x y z]
             (== (llist 'e 'a 'd x) r)
             (conso y (llist 'a z 'c) r)))
;; => ((e a d . c))

;; Frame 2:25
(run* [x]
      (conso x (llist 'a x 'c) (llist 'd 'a x 'c)))
;; => (d)

;; Frame 2:26
(run* [l]
      (fresh [x]
             (== (llist 'd 'a x 'c) l)
             (conso x (llist 'a x 'c) l)))
;; => ((d a d . c))

;; Frame 2:27
(run* [l]
      (fresh [x]
             (conso x (llist 'a x 'c) l)
             (== (llist 'd 'a x 'c) l)))
;; => ((d a d . c))

;; Frame 2:29
(run* [l]
      (fresh [d x y w s]
             (conso w (llist 'a 'n 's) s)
             (resto l s)
             (firsto l x)
             (== 'b x)
             (resto l d)
             (firsto d y)
             (== 'e y)))
;; => ((b e a n . s))

;; Frame 2:30
(empty? '(grape raisin pear))
;; => false

;; Frame 2:31
(empty? '())
;; => true

;; Frame 2:32
(run* [q]
      (emptyo '(grape raisin pear))
      (== true q))
;; => ()

;; Frame 2:33
(run* [q]
      (emptyo '())
      (== true q))
;; => (true)

;; Frame 2:34
(run* [x]
      (emptyo x))
;; => (())

;; Frame 2:36
(defn eq [x y]
  (= x y))

(eq 'pear 'plum)
;; => false

;; Frame 2:37
(eq 'plum 'plum)
;; => true

;; Frame 2:38
(defn eqo [x y]
  (== x y))

(run* [q]
      (eqo 'pear 'plum)
      (== true q))
;; => ()

;; Frame 2:39
(run* [q]
      (eqo 'plum 'plum)
      (== true q))
;; => (true)

;; Frame 2:43
(defn pair? [x]
  (or (lcons? x) (and (coll? x) (boolean (seq x)))))

(pair? (llist '(split) 'pea))
;; => true

;; Frame 2:44
(pair? '())
;; => false

;; Frame 2:48
(first '(pear))
;; => pear

;; Frame 2:49
(rest '(pear))
;; => ()

;; Frame 2:51
(lcons '(split) 'pea)
;; => ((split) . pea)

;; Frame 2:52
(run* [r]
      (fresh [x y]
             (== (llist x y 'salad) r)))
;; => ((_0 _1 . salad))

;; Frame 2:54
(defn pairo [p]
  (fresh [a d]
         (conso a d p)))

(run* [q]
      (pairo (lcons q q))
      (== true q))
;; => (true)

;; Frame 2:55
(run* [q]
      (pairo '())
      (== true q))
;; => ()

;; Frame 2:56
(run* [q]
      (pairo 'pair)
      (== true q))
;; => ()

;; Frame 2:57
(run* [x]
      (pairo x))
;; => ((_0 . _1))
