## Number (0-9) Recognizer
### Javafx application integrated with tensorflow model built in python

## MNIST Datasets
https://yann.lecun.com/exdb/mnist/
#### The Dataset used is MNIST Dataset. It is a subset of a larger set available from NIST.
#### The digits have been size-normalized and centered in a fixed-size image. 
#### The MNIST problem is a dataset developed by Yann LeCun, Corinna Cortes and 
#### Christopher Burges for evaluating machine learning models on the handwritten digit
#### classification problem. Each image is a 28 by 28 pixel square (784 pixels total).
#### A standard spit of the dataset is used to evaluate and compare model, where 60,000 images 
#### are used to train a model and a separate set of 10,000 images are used to test it.


#### Use MNIST datasets in python using tesnsorflow keras
#### Use CNN (Convolutional neural network) model and sequential model for training 
#### This application first use python script to train and save model, 
#### Then use java library tensorflow to load the model and make prediction

## Image preprocessing
#### Preprocessing of input image is required before feeding it to model
#### Get resized and centered image
#### Normalize from [0, 255] to [0.0, 1.0] and  color can be flipped according to python model
#### Reshape 2D array to 4D array format (This again depends on the model being used)



![Screenshot of application](C:\Users\auro0\OneDrive\Desktop\test.png "Screenshot of application")
