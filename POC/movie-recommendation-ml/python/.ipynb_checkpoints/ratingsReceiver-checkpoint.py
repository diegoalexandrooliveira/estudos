#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pika
import json
import ratingsLearning


# In[2]:


def callback(ch, method, properties, body):
    ratingsDict = json.loads(body)
    movies = []
    users = []
    ratings = []    
    for rating in ratingsDict:
        movies.append(rating["movieImdbId"])
        users.append(rating["userId"])
        ratings.append(rating["rating"])
    ratingsLearning.trainingRatings(movies, users, ratings)


# In[ ]:


connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()
channel.queue_declare(queue='ratingsForTraining')
channel.basic_consume(queue='ratingsForTraining',
                      auto_ack=True,
                      on_message_callback=callback)
channel.start_consuming()


# In[ ]:





# In[ ]:





# In[ ]:




