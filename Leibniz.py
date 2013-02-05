#76 Characters
for _ in [1]*input():print'%.15f'%sum((-1.)**i/(2*i+1)for i in range(input()))
#87 Characters
#for a in range(input()):print("%.15f"%sum([(-1)**i/(2*i+1.0) for i in range(input())]))
#95 Characters
#T=input();
#while T:
# T-=1;x=0.0
# for i in range(input()):x+=(-1)**i/(2*i+1.0)
# print("%.15f"%x)
#109 Characters
#T=int(input());
#while T:
# T-=1;n=int(input());x=0.0
# for i in range(n):x+=(-1)**i/(2*i+1.0)
# print("%.15f"%x)
#193 Characters
#T=int(input())
#for a in range(T):
#    n=int(input())
#    x=0.0
#    for i in range(n):
#        if i%2==0:
#            x+=1.0/(2*i+1)
#        else:
#            x-=1.0/(2*i+1)
#    print("%.15f"%x)