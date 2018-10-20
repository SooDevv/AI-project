# Handwritten Digits Recognition

## Android Demo

![](106_6.gif)

## Description

The main purpose of this project is to understand and implement with TensorFlow Lite on Android.<br>
The data is MNIST which is a simple computer vision dataset, consisting of images of handwritten digits and labels. which is famous on Deep learning pattern recognition.<br>
The model i used is MLP(Multi layer perceptron) and CNN(Convolution neural networks) and etc

## Environment

- Window10
- Colab
- Python.3.6, Keras, TFLite
- Android Studio 3.0, Gradle 3.1.3

## Data set
- [MNIST](http://yann.lecun.com/exdb/mnist/)

## 실행 방법
```
$ App Name
.
├── manifest
│   └── AndroidManifest.xml
├── java
│   ├── Classifier
│   ├── MainActivity
│   └── Result
├── assets
│   └── mnist_mlp.tflite
├── res
│   ├── drawble   
│   ├── layout  
│   │   └── activity_main.xml
│   ├── mipmap
│   └── values
│       ├── colors.xml
│       ├── strings.xml
│       └── styles.xml
└── Gradle
    ├── build.gradle(Project:MNIST-Android)
    └── build.gradle(Module:app)
```

## Reference

- Ryan Gotesman - [How to Bring Keras Models onto Android with ZERO Knowledge of Tensorflow](https://towardsdatascience.com/how-to-convert-from-keras-to-tflite-with-zero-knowledge-of-tensorflow-5448a296ae67)
- nex3z's Android demo : [tflite-mnist-android](https://github.com/nex3z/tflite-mnist-android/blob/master/README.md)
