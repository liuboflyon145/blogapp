(ns myapp.middleware 
	(:use noir.request)
	(:require 
		[myapp.models.config :as config] 
		[myapp.models.db :as db]
		) )


(defn set-context []
	( assoc {} 
	   :blogname (config/get-value "blogname")
       :nickname (config/get-value "nickname") 
       :tags (db/group-by-tag)
))

(defn base-handler [handler]
	(fn [req] 
		(let [wrap-req (assoc req :context (set-context))
				  resp (handler wrap-req)]
			;(println resp)
			resp)))

