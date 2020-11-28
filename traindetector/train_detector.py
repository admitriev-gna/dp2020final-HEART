import math

import cv2
import imutils as imutils
import numpy as np


def size_vector(a, b):
    return math.sqrt((a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2)


def compare_vec(a, b, c, d):
    return abs(size_vector(a, b) - size_vector(c, d))


protoFile = "pose_deploy_linevec.prototxt"
weightsFile = "pose_iter_160000.caffemodel"

net = cv2.dnn.readNetFromCaffe(protoFile, weightsFile)
nPoints = 15
POSE_PAIRS = [[0, 1], [1, 2], [2, 3], [3, 4], [1, 5], [5, 6], [6, 7], [1, 14], [14, 8], [8, 9], [9, 10], [14, 11],
              [11, 12], [12, 13]]

print("Using CPU device")

cap = cv2.VideoCapture("IMG_4225.mov")
ret, img = cap.read()

vid_writer = cv2.VideoWriter('output_I.avi',cv2.VideoWriter_fourcc('M','J','P','G'), 10, (368, 368))
k = 0
while cap.isOpened():
    ret, img = cap.read()
    img = cv2.resize(img, (368, 368))
    img = imutils.rotate_bound(img, 90)
    if not ret:
        break
    frameCopy = np.copy(img)

    frameWidth = img.shape[1]
    frameHeight = img.shape[0]
    inHeight = 368
    inWidth = 368

    threshold = 0.1

    inpBlob = cv2.dnn.blobFromImage(img, 1.0 / 255, (inWidth, inHeight), (0, 0, 0), swapRB=False, crop=False)
    net.setInput(inpBlob)
    output = net.forward()

    H = output.shape[2]
    W = output.shape[3]
    points = []

    for i in range(nPoints):
        probMap = output[0, i, :, :]
        minVal, prob, minLoc, point = cv2.minMaxLoc(probMap)
        x = (frameWidth * point[0]) / W
        y = (frameHeight * point[1]) / H
        if prob > threshold:
            cv2.circle(frameCopy, (int(x), int(y)), 8, (0, 255, 255), thickness=-1, lineType=cv2.FILLED)

            # Add the point to the list if the probability is greater than the threshold
            points.append((int(x), int(y)))
        else:
            points.append(None)

    try :
        if compare_vec(points[9], points[14], points[12], points[14]) <= 10 and compare_vec(points[7], points[5], points[4], points[2]) < 20  and compare_vec(points[2], points[1], points[5],
                                                                                            points[1]) <= 10:
            cv2.putText(frameCopy, "GOOD", (0, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 3,
                        lineType=cv2.LINE_AA)
        else:
            cv2.putText(frameCopy, "BAD", (0, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 3,
                        lineType=cv2.LINE_AA)
    except :
        cv2.putText(frameCopy, "GOOD", (0, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 3,
                    lineType=cv2.LINE_AA)

    cv2.imshow('Output-Keypoints', frameCopy)
    # cv2.imwrite("res/img" + str(k) + ".jpg",)
    vid_writer.write(frameCopy)
    # cv2.imshow('Output-Skeleton', img)

    key = cv2.waitKey(10)
    if key == 27:
        break

vid_writer.release()

cap.release()
cv2.destroyAllWindows()
