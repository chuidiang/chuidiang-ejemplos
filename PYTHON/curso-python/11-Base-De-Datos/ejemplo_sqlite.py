import sqlite3

con = sqlite3.connect('example.db')

cur = con.cursor()

# Create table
cur.execute('''CREATE TABLE stocks
               (date text, trans text, symbol text, qty real, price real)''')

# Insert a row of data
cur.execute("INSERT INTO stocks VALUES ('2006-01-05','BUY','RHAT',100,35.14)")
cur.execute("INSERT INTO stocks VALUES ('2006-01-06','SELL','KO',50,63.16)")
cur.execute("INSERT INTO stocks VALUES ('2006-01-07','BUY','JNJ',150,170.24)")

# Save (commit) the changes
con.commit()

res = cur.execute('SELECT * FROM stocks')

print (res.fetchone())

# We can also close the connection if we are done with it.
# Just be sure any changes have been committed or they will be lost.
con.close()
