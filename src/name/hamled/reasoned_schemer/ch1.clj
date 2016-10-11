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

;; Frame 1:32
(run* [r]
      (fresh [x]
             (let [y x]
               (fresh [x]
                      (== (llist y x y) r)))))
;; => ((_0 _1 . _0))

;; Frame 1:33
(run* [r]
      (fresh [x]
             (let [y x]
               (fresh [x]
                      (== (llist x y x) r)))))
;; => ((_0 _1 . _0))

;; Frame 1:34
(run* [q]
      (== false q)
      (== true q))
;; => ()

;; Frame 1:35
(run* [q]
      (== false q)
      (== false q))
;; => (false)

;; Frame 1:36
(run* [q]
      (let [x q]
        (== true x)))
;; => (true)

;; Frame 1:37
(run* [r]
      (fresh [x]
             (== x r)))
;; => (_0)

;; Frame 1:38
(run* [q]
      (fresh [x]
             (== true x)
             (== x q)))
;; => (true)

;; Frame 1:39
(run* [q]
      (fresh [x]
             (== x q)
             (== true x)))
;; => (true)

;; Frame 1:41
(cond
  false true
  :else false)
;; => false

;; Frame 1:47
(run* [x]
      (conde
        ((== 'olive x) s#)
        ((== 'oil x) s#)
        (:else u#)))
;; => (olive oil)

;; Frame 1:49
(run 1 [x]
     (conde
       ((== 'olive x) s#)
       ((== 'oil x) s#)
       (:else u#)))
;; => (olive)

;; Frame 1:50
(run* [x]
      (conde
        ((== 'virgin x) u#)
        ((== 'olive x) s#)
        (s# s#)
        ((== 'oil x) s#)
        (:else u#)))
;; => (olive _0 oil)

;; Frame 1:52
(run 2 [x]
      (conde
        ((== 'extra x) s#)
        ((== 'virgin x) u#)
        ((== 'olive x) s#)
        ((== 'oil x) s#)
        (:else u#)))
;; => (extra olive)

;; Frame 1:53
(run* [r]
      (fresh [x y]
        (== 'split x)
        (== 'pea y)
        (== (llist x y) r)))
;; => (split . pea)

;; Frame 1:54
(run* [r]
      (fresh [x y]
             (conde
               ((== 'split x) (== 'pea y))
               ((== 'navy x) (== 'bean y))
               (:else u#))
             (== (llist x y) r)))
;; => ((split . pea) (navy . bean))

;; Frame 1:55
(run* [r]
      (fresh [x y]
             (conde
               ((== 'split x) (== 'pea y))
               ((== 'navy x) (== 'bean y))
               (:else u#))
             (== (llist x y 'soup) r)))
;; => ((split pea . soup) (navy bean . soup))

;; Frame 1:56
(defn teacupo [x]
  (conde
    ((== 'tea x) s#)
    ((== 'cup x) s#)
    (:else u#)))

(run* [x]
      (teacupo x))
;; => (tea cup)

;; Frame 1:57
(run* [r]
      (fresh [x y]
             (conde
               ((teacupo x) (== true y) s#)
               ((== false x) (== true y))
               (:else u#))
             (== (llist x y) r)))
;; => ((false . true) (tea . true) (cup . true))

;; Frame 1:58
(run* [r]
      (fresh [x y z]
             (conde
               ((== y x) (fresh [x] (== z x)))
               ((fresh [x] (== y x)) (== z x))
               (:else u#))
             (== (llist y z) r)))
;; => ((_0 . _1) (_0 . _1))

;; Frame 1:59
(run* [r]
      (fresh [x y z]
             (conde
               ((== y x) (fresh [x] (== z x)))
               ((fresh [x] (== y x)) (== z x))
               (:else u#))
             (== false x)
             (== (llist y z) r)))
;; => ((false . _0) (_0 . false))

;; Frame 1:60
(run* [q]
      (let [a (== true q)
            b (== false q)]
        b))
;; => (false)

;; Frame 1:61
(run* [q]
      (let [a (== true q)
            b (fresh [x]
                     (== x q)
                     (== false x))
            c (conde
                ((== true q) s#)
                (:else (== false q)))]
        b))
;; => (false)
