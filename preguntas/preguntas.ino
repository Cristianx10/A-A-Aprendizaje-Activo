int potenciometro = 0;
boolean validar;
int counter =0;
char sobre[20];
String recibido;

int opcion = 0;
int opcionTemp = 0;

int pul = 0;
int pulTemp = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(9, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  
  potenciometro = analogRead(A0);

  opcion = map(potenciometro, 0, 1000, 1, 4);

  if(opcionTemp != opcion){
    Serial.println(opcion);
    opcionTemp = opcion;
  }
  
  
  pul = digitalRead(9);

  if(pulTemp != pul){
    if(pul+5 == 6){
     Serial.println(6);
    }
    pulTemp = pul;
  }
  
  

  recibirDatos();
  
  
delay(200);
}

void recibirDatos(){
  
}

void serialEvent(){

    int inByte = Serial.read(); //read the incoming byte
   

     if(inByte == 8){
      digitalWrite(2, HIGH);
   
    }

if(inByte == 9){
      digitalWrite(2, LOW);
      digitalWrite(3, LOW);
    }


    if(inByte == 10){
      
      digitalWrite(3, HIGH);
    }

if(inByte == 11){
    
      digitalWrite(3, LOW);
    }

    
      
}


