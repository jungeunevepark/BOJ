def merge_sort(A):

    if (len(A) <= 1):
        return A

    mid = int(len(A)/2)

    left = A[:mid]
    right = A[mid:]
    left = merge_sort(left)
    right = merge_sort(right)
    return merge_array(left, right)


def merge_array(left, right):

    i = 0
    j = 0
    B = []

    while (i < len(left)) or (j < len(right)):

        if ((i < len(left)) and (j < len(right))):
            if (left[i][1] < right[j][1]):
                B.append(left[i])
                i += 1
            elif (left[i][1] == right[j][1]):
                if (left[i][0] < right[j][0]):
                    B.append(left[i])
                    i += 1
                else:
                    B.append(right[j])
                    j += 1
            else:
                B.append(right[j])
                j += 1
        else:
            if (i == len(left)):
                while (j < len(right)):
                    B.append(right[j])
                    j += 1
            else:
                while (i < len(left)):
                    B.append(left[i])
                    i += 1

    return B


test = int(input())  # 입력받을 개수를 받는다

A = []

for i in range(0, test):
    a, b = map(int, input().split())
    t = a, b
    A.append(t)

A = merge_sort(A)

sum = 0
for i in range(0, len(A)):
    if len(A) == 1:
        break

    if (i == 0):
        if (A[i][1] == A[i+1][1]):  # 맨 앞
            sum += A[i+1][0] - A[i][0]
        else:
            sum += 0

    elif (i == len(A)-1):
        if (A[i][1] == A[i-1][1]):  # 맨 뒤
            sum += A[i][0] - A[i-1][0]
        else:
            sum += 0

    else:
        if (A[i][1] != A[i+1][1]) and (A[i][1] != A[i-1][1]):  # 색이 겹치는 점이 아예 없음 :솔로
            sum += 0

        elif (A[i][1] != A[i+1][1]):  # 뒤랑 색이 다름
            sum += A[i][0] - A[i-1][0]

        elif (A[i][1] != A[i-1][1]):  # 앞과 색이 다름
            sum += A[i+1][0] - A[i][0]

        else:  # 앞, 뒤 둘다 색이 같음
            if((A[i][0] - A[i-1][0]) < (A[i+1][0] - A[i][0])):
                sum += A[i][0] - A[i-1][0]  # 앞이랑 더 가까움

            else:
                sum += A[i+1][0] - A[i][0]  # 뒤랑 더 가까움

print(sum)