T=input()
for a in range(T):
    N=input()
    b=[int(next) for next in raw_input().split(' ')]
    ans=1
    for x in b:
        ans*=x**(x-1)
    ans*=sum(b)**(N-2)
    print int(ans)%1000000007