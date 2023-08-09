# UYGULAMA GEREKSİNİMLERİ VE AÇIKLAMALAR
## 1- Gereksinimler
    - docker mongodb kurulum komutları
    docker run -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=192837 -p 27017:27017 mongo
     https://www.mongodb.com/try/download/compass
    - Mongodb compass tool açıldıktan sonra 
     # Advanced Connection Options a tıklayıp açın
     # Host kısmına bağlantı yapılacak bilgisayar ve mongodb nin çalıştığı portu yazın
        localhost:27017
     # Authentication kısmına tıklayın ve burada username/password seçeneğini seçin
     # Username kısmına admin, password kısmına ise root yazın (Mongodb yi docker üzerinde çalıştırırken username
      ve password kısmını yukarıda belirtilen şekilde girdiğimiz için aynı şekilde yazmamız gerekmektedir.
     # Authentication Database kısmını boş bırakıyoruz çünkü yönetici kullanıcısının herhangi bir db ye bağlanması
      gerekmez tüm sistemi yönetebilir. Ancak bir kullanıcı ekler ve ona bir DB tanımlar iseniz o zaman bu kısma
      giriş yapmanız gereklidir.
     # Açılan pencerede + butonuna tıklayarak yeni bir db ekleyin, db adı ve örnek bir collection adı girin.
     *** DİKKAT: Buradan itibaren oluşturduğumuz DB için onu yönetecek bir kullanıcı oluşturacağız.
     * ilk olarak mongodb compass in sol köşesinde bulunan MONGOSH a tıklayıp consol ekranını açıyoruz.
     * Burada işlem yapmak istediğimiz DB nin adı ile birlikte;
     - use <DB ADI> yazıp entera basıyoruz
     + ilgili db ye geçtikten sonra;
     - db.createUser({user:"admin12",pwd:"ugur**",roles:["readWrite","dbAdmin"]})
     # yukarıda bilgileri verilen kullanıcı ile artık belirtilen veritabanında işlem yapabiliriz.

# RabbitMQ için işlemler
    - Docker'a kurulum için gerekli yapılandırmaları yapalım. Mutlaka portlar eklenmeli(5672 ve 15672)
    - docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=uguryagmur4 -e RABBITMQ_DEFAULT_PASS=root** -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    - RabbitMQ'ya java içinden bağlanmak için aşağıdaki linki kullanabilirsiniz.
      localhost:5672
# REDIS için gerekli işlemler
    - docker run --name ugurredis -p 6379:6379 -d redis
    Dikkat !!
    - Redis bağlantılarını ayarlamak için gerekli olan kodlamaları yaparken host ve port u girmek gereklidir. Ancak 
    bu bilgileri direkt kod içinde yazmak yerine application.yml üzerinden almak daha mantıklıdır. Böylece environment
    variable  ile deploy içinde bu bilgileri alabiliriz.
# PROJENİN DEPLOY EDİLMESİ
    1- Uygulamanın gradle ile build edilmesi gereklidir
        1.1- sağ taraftan gradle sekmesine tıklayın
        1.2- çoklu yapı olduğu içprojenin adını seçip "config-server-git"
        1.3- Tasks>build>build çift tıklayıp projeyi oluşturun
        1.4- Task>build>buildDependent çift tıklayıp projeyi oluşturun
    2- Bu işlemden sonra proje dosyasının altında oluşan build klasörünün içindeki libs
        klasörünün içine projenizin jar dosyası eklenmiş olur. Bu dosya direkt çalıştıralabilir
    3- Dockerfile oluşturuyoruz
    4- consol(Terminal) ekranında bu dockerfile ile imajı oluşturuyoruz
        4.1- docker build -t config-server-git > DİKKAt bu imajı buluta atmak için repo adını kullanın 
        4.2- docker build -t ugurdev95/java8sonfigservergit:v01 .
        4.3- docker build -t ugurdev95/java8authservice:v01 .
        4.4- docker build -t ugurdev95/java8userservice:v01 .
        4.5- docker build -t ugurdev95/java8apigatewayservice:v01 .
 
