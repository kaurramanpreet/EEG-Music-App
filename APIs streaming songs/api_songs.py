from flask import Flask, Response, render_template
from flask import request, jsonify,g
import sqlite3

app = Flask(__name__,template_folder="templates")
DATABASE = './api_database.db'


def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
        db.row_factory = sqlite3.Row
    return db

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()	


@app.route('/', methods=['GET'])
def home():
    return '''<h1>WELCOME TO TASK 2</h1>
<p>ENTER URLs</p>
<p> ENTER INPUT IN THE FOLLOWING FORM TO ACCESS THE CORRECT URL</P>
<H4>http://127.0.0.1:5000/all/check?input=( <H2> PLACE SERVER ID HERE </H2> ,<H2> PLACE CPU_UTILIZATION PERCENTAGE HERE </H2>,<H2>PLACE MEMORY_UTILIZATION PERCENTAGE HERE
</H2>,<H2>PLACE DISK_UTILIZATION PERCENTAGE HERE</H2>)</H4>'''

@app.route("/mpeg")
def streamwav():
    if 'mood' in request.args:
        mood_type = (request.args['mood'])
    if 'song' in request.args:
        song_name = (request.args['song'])
    def generate(mood_type,song_name):
        with open(mood_type+"/"+song_name+".mp3", "rb") as fwav:
            data = fwav.read(1024)
            while data:
                yield data
                data = fwav.read(1024)
    return Response(generate(mood_type,song_name), mimetype="audio/mp3")

@app.route("/list")
def list():
    if 'age' in request.args:
        age = (request.args['age'])
    if 'genre' in request.args:
        genre = (request.args['genre'])
    if 'lang' in request.args:
        lang = (request.args['lang'])
    def query_db(query, args=(), one=False):
        cur = get_db().execute(query, args)
        rv = cur.fetchall()
        cur.close()
        return (rv[0] if rv else None) if one else rv
    list=[]
    list1= query_db("select * from songs_data where age=? and genre=? and lang=?",(age,genre,lang))
    print(type(list1))
    for row in list1:
        diction= {'title':str(row['title']),'artist':row['artist'],'mood':row['folder'],'song':row['song_name']}
        list.append(diction)
    return jsonify(list)

@app.route('/newDatabase')
def new_database():
   return render_template('database.html')
        
@app.route('/database',methods=['POST'])
def database():
    msg="raman"
    try:
        print(request.form)
        msg="entered tru"
        title =request.form['title']
        title = title.encode('utf-8')
        folder = request.form['city'].encode('utf-8')
        
        artist = request.form['artist'].encode('utf-8')
        song_name = request.form['pin'].encode('utf-8')
        age = request.form['age'].encode('utf-8')
        genre = request.form['genre'].encode('utf-8')
        lang = request.form['lang'].encode('utf-8')
        msg="here i am"
        print(type(genre))
        with sqlite3.connect("api_database.db") as con:
            cur = con.cursor()
            msg = "enjdb vejkn"
            cur.execute("INSERT INTO songs_data (title,artist,folder,song_name,age,genre,lang) VALUES (?,?,?,?,?,?,?)", (title,artist,folder,song_name,age,genre,lang) )
            msg = "error ijefjcbqqqqqqqqqqq"
            con.commit()
            msg = "Record successfully added"
    except:
        msg = "error in insert operation"
        return render_template("./result.html",msg = msg)
        
    finally:
        return render_template("./result.html",msg = msg)
        


@app.route("/ogg")
def streamogg():
    def generate():
        with open("signals/song.ogg", "rb") as fogg:
            data = fogg.read(1024)
            while data:
                yield data
                data = fogg.read(1024)
    return Response(generate(), mimetype="audio/ogg")

if __name__ == "__main__":
    app.run(host="0.0.0.0",debug=True)

