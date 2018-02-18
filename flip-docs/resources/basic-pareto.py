import numpy as np
from scipy.stats import pareto
import matplotlib as mpl
import matplotlib.pyplot as plt
import preprocessing as prep

name = "basic-pareto"
dir = "../../flip-bench/experiments/basic-pareto/"

# PDF

xmin = 0
xmax = 10
ymax = 1

fig = plt.figure(figsize=(20, 5))

data_locs = [
    dir + "basic-pareto-pdf-40.out",
    dir + "basic-pareto-pdf-60.out",
    dir + "basic-pareto-pdf-220.out"
]

i = 1
for data_loc in data_locs:
    data_xmin = xmin - 10
    data_xmax = xmax + 10

    raw_dat = prep.data(data_loc, 3)
    dat = prep.transform(raw_dat, data_xmin, data_xmax)

    x1, xerr1, y1 = prep.unzip(dat)
    x2 = np.arange(data_xmin, data_xmax, 0.1)
    y2 = pareto.pdf(x2, 1)

    ax = plt.subplot(1, len(data_locs) + 1, i)
    ax.errorbar(x1, y1, xerr=xerr1, fmt='.')
    ax.plot(x2, y2, '--')
    ax.set_xlim(xmin, xmax)
    ax.set_ylim(0, ymax)
    ax.set_ylabel("pdf")
    ax.set_xlabel("x")

    i = i + 1


# KLD

kld_data_loc = dir + "basic-pareto-kld.out"

kld_data = prep.data(kld_data_loc, 2)

x3 = list(map(lambda d: d[0], kld_data))
y3 = list(map(lambda d: d[1], kld_data))
ax = plt.subplot(1, len(data_locs) + 1, len(data_locs) + 1)
ax.plot(x3, y3)
ax.axvline(50, color='r', linestyle=':', linewidth=1)
ax.axvline(150, color='r', linestyle=':', linewidth=1)
ax.axvline(250, color='r', linestyle=':', linewidth=1)
ax.set_ylabel("divergence")
ax.set_xlabel("update count")
ax.set_xlim(40, 300)
ax.set_ylim(0.10, 0.4)

plt.legend()
plt.savefig(name + '.pdf')
plt.show()