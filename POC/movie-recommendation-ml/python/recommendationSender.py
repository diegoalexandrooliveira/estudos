#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pika
import json

def sendRecommendations(movies, users, ratings):
    payload = []
    length = len(movies)
    for i in range(length):
        payload.append({
            "imdb_id": movies[i],
            "user_id": users[i],
            "rating": ratings[i]
        })
    jsonPayload = json.dumps(payload)
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='recommendations')
    channel.basic_publish('','recommendations',jsonPayload)
    connection.close()


# In[ ]:




