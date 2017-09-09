

import tensorflow as tf
import matplotlib.pyplot as plt


X_AXIS = [150, 200, 220, 250, 300,    600, 650, 700]
Y_AXIS = [  1,   1,   2,   3,   3,      4,   5,   5]

points = []
for index, point in enumerate(X_AXIS):
    points.append([point, Y_AXIS[index]])

K_MEANS_RESULT = tf.contrib.learn.KMeansClustering(2, './model')

def train():
    data = tf.constant(points, tf.float32)
    return (data, None)

K_MEANS_RESULT.fit(input_fn=train, steps=1)

CLUSTERS = K_MEANS_RESULT.get_variable_value('clusters')
LEFT_CENTROID = CLUSTERS[0]
RIGHT_CENTROID = CLUSTERS[1]

plt.plot(X_AXIS, Y_AXIS, 'bo')
plt.plot([LEFT_CENTROID[0], RIGHT_CENTROID[0]], [LEFT_CENTROID[1], RIGHT_CENTROID[1]], 'rx')
plt.show()
