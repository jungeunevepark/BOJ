N, M = map(int, input().split())
sky = [list(map(int, input().split())) for _ in range(N)]
moves = [list(map(int, input().split())) for _ in range(M)]

clouds = [(N-1, 0), (N-1, 1), (N-2, 0), (N-2,1)]

dx = [0, 0, -1, -1, -1, 0, 1, 1, 1]
dy = [0, -1, -1, 0, 1, 1, 1, 0, -1]

def move_cloud(clouds, sky):
    new_skys = [[0]*N for _ in range(N)]
    new_clouds = []
    for x, y in clouds:
        nx = x+(s % N)*dx[d]
        ny = y+(s % N)*dy[d]
        if nx < 0:
            nx += N
        elif nx >= N:
            nx -= N
        if ny < 0:
            ny += N
        elif ny >= N:
            ny -= N
        new_clouds.append((nx, ny))
        new_skys[nx][ny] = 1
        sky[nx][ny] += 1
    return new_clouds, sky, new_skys


def copy_water(clouds,sky):
    for i, j in clouds:
        count = 0
        for ni, nj in [(i-1, j-1), (i+1, j-1), (i+1, j+1), (i-1, j+1)]:
            if 0 <= ni < N and 0 <= nj < N and sky[ni][nj] > 0:
                count += 1
        sky[i][j] += count
    return sky

def make_clouds(sky, cloud):
    make_cloud = []
    for i in range(N):
        for j in range(N):
            if sky[i][j] >= 2 and cloud[i][j] == 0:
                sky[i][j] -= 2
                make_cloud.append((i, j))
    return make_cloud

for d, s in moves:
    new_clouds, sky, new_skys = move_cloud(clouds, sky)
    sky = copy_water(new_clouds, sky)
    clouds = make_clouds(sky, new_skys)
print(sum(sky[i][j] for i in range(N) for j in range(N)))