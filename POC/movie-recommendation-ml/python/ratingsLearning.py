#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
from surprise import Dataset
from surprise import Reader
from surprise import NMF
import recommendationSender


# In[2]:


def trainingRatings(movies, users, ratings):
    ratings_dict = {
    "movies": movies,
    "users": users,
    "ratings": ratings
    }
    df = pd.DataFrame(ratings_dict)
    reader = Reader(rating_scale=(1, 5))
    data = Dataset.load_from_df(df[["users", "movies", "ratings"]], reader)
    trainingSet = data.build_full_trainset()
    algo = NMF(n_factors=100, n_epochs=100, reg_pu=0.01)
    algo.fit(trainingSet)
    recommendMoviesForUsers(movies, users, algo)


# In[3]:


def recommendMoviesForUsers(allMovies, allUsers, algo):
    movies = []
    allMovies = sorted(allMovies)
    lastMovie = ""
    for movie in allMovies:
        if(lastMovie != movie):
            lastMovie = movie
            movies.append(movie)
    users = []
    allUsers = sorted(allUsers)
    lastUser = ""
    for user in allUsers:
        if(lastUser != user):
            lastUser = user
            users.append(user)
    ratingsMovie = []
    ratingsUser = []
    ratings = []
    for user in users:
        for movie in movies:
            ratingsMovie.append(movie)
            ratingsUser.append(user)
            ratings.append(algo.predict(user, movie).est)
    recommendationSender.sendRecommendations(ratingsMovie, ratingsUser, ratings)


# In[ ]:





# In[ ]:




