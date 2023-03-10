//효율적 db사용하기 위해 dog db제어할 클래스를 만들어준다
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';

import '../model/dog.dart';
class DBHelper{
  DBHelper _privateConstructor();
  static final DBHelper instance = DBHelper _privateConstructor();

  static Database? _database;

}

