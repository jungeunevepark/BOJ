from collections import deque

def dfs(x):
    visited[x] = True
    print(x, end=' ')
    for i in graph[x]:
        if not visited[i]:
            dfs(i)


def bfs(x):
    visited[x] = True
    queue = deque([x])
    while queue:
        i = queue.popleft()
        print(i, end=' ')
        for k in graph[i]:
            if not visited[k]:
                queue.append(k)
                visited[k] = True


N, M, V = map(int, input().split())
list = [list(map(int, input().split())) for _ in range(M)]
graph = [[] for _ in range(N+1)]
for x, y in list:
    graph[x].append(y)
    graph[y].append(x)
for i in range(1, N+1):
    graph[i].sort()
visited = [False] * (N+1)
dfs(V)
print()
visited = [False] * (N+1)
bfs(V)