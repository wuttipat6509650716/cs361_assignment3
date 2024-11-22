package com.example.cs361assignment3;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Random random = new Random();
    SensorManager sensorManager;
    private static final int POLL_INTERVAL = 500;
    private Handler hdr = new Handler();
    Boolean shown_dialog = false;
    SensorInfo sensor_info = new SensorInfo();
    private static final int shake_threshold = 12;

    private final Runnable pollTask = new Runnable() {
        public void run() {
            showDialog();
            hdr.postDelayed(pollTask, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);

    }//end OnCreate
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    public void onSensorChanged(SensorEvent event){
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            sensor_info.accX=event.values[0];
            sensor_info.accY=event.values[1];
            sensor_info.accZ=event.values[2];
        }
    }

    private ArrayList<String> getPredictions() {
        ArrayList<String> predictions = new ArrayList<>();
        String currentLanguage = Locale.getDefault().getLanguage();
        if(currentLanguage.equals("th")){
            predictions.add("ดวงดาวที่มีพลังและยิ่งใหญ่ มีความกล้าหาญและไหวพริบ มีความคิดที่ยอดเยี่ยมและมีคุณสมบัติความเป็นผู้นำที่มีชื่อเสียง มีความมุ่งมั่น ทะเยอทะยาน และชอบผจญภัย ชอบสำรวจทุกที่และสนุกกับประสบการณ์ใหม่ๆ เป็นที่เคารพนับถืออย่างสูงจากหลาย ๆ คน");
            predictions.add("เป็นคนช่างฝัน มีอารมณ์ศิลป์ มองโลกในแง่ดี และมีทักษะในการแก้ปัญหา เป็นคนมีน้ำใจและเห็นอกเห็นใจผู้อื่น");
            predictions.add("รักอิสระแต่ขาดสมาธิ ชอบแสวงหาความรู้ และปรารถนาที่จะศึกษาในระดับสูง พูดตรงไปตรงมาและเห็นคุณค่าของความยุติธรรม มีน้ำใจ นิสัยใจกว้าง ควรระมัดระวังเรื่องสุขภาพที่เกี่ยวข้องกับระบบประสาทและหลีกเลี่ยงการทำงานหนักเกินไป");
            predictions.add("เป็นคนฉลาดและมีไหวพริบ มีไหวพริบ และเข้าใจผู้คนได้ดี มีทักษะในการนำเสนอตัวเองอย่างเหมาะสมในทุกสถานการณ์ ชอบการเดินทางไกล บางครั้งพวกเขาอาจรู้สึกว่าขาดเพื่อนแท้ แต่ความฉลาดของพวกเขาทำให้พวกเขาประสบความสำเร็จในชีวิต");
            predictions.add("บุคคลผู้มีความรู้และสติปัญญาที่เชื่อถือได้ และได้รับความเคารพนับถือจากผู้อื่น โดยทั่วไปงานของพวกเขาดำเนินไปอย่างราบรื่น โดยมีคุณธรรม ความเมตตา และความเห็นอกเห็นใจที่แข็งแกร่ง มุ่งสู่จิตวิญญาณและความชอบธรรม");
            predictions.add("มีชีวิตที่สุขสบาย สนุกสนาน เต็มไปด้วยจินตนาการ รักศิลปะ และเสรีภาพ ชอบพื้นที่ส่วนตัว ไม่ทำร้ายผู้อื่น มีจิตใจเมตตากรุณา เป็นที่รักของใครหลายๆ คน มีเสน่ห์ และดำรงตำแหน่งที่น่านับถือ ด้วยความมั่งมีและเจริญรุ่งเรือง");
            predictions.add("บุคคลผู้รักอิสระ มีความรู้สึกเป็นปัจเจกชน ไม่สนใจติดตามผู้อื่น หลงใหลในศิลปะ การเขียน การวาดภาพ หรือบทกวี และมีความสามารถเฉพาะตัวในการแก้ปัญหา ใจกว้างและใจบุญโดยธรรมชาติ");
            predictions.add("เป็นคนเงียบๆ ขี้อาย ชอบสันโดษ ไม่ค่อยเข้าสังคมกับคนอื่น ตอนเป็นเด็กอาจมีปัญหาเรื่องสุขภาพแต่จะแข็งแกร่งขึ้นตามวัยและอายุยืนยาว มีอารมณ์ขันเล็กน้อย แม้ว่าพวกเขาจะไม่ค่อยแสดงออก แต่มักจะดูจริงจัง พวกเขามักจะปล่อยให้ความคิดเห็นของผู้อื่นมาแทนที่ความคิดของตนเอง");
            predictions.add("ดูเย่อหยิ่งแต่ขาดความมั่นใจในตนเองลึกๆ เป็นคนจริงจัง ตั้งใจ แต่มักตัดสินใจหุนหันพลันแล่น แล้วมาเสียใจภายหลัง ซื่อสัตย์ ตรงไปตรงมา ฉุนเฉียวแต่ให้อภัยได้เร็วพอๆ กัน ทั้งที่พยายามจะ ช่วยเหลือผู้อื่น การมีส่วนร่วมของพวกเขามักจะไม่มีใครรับรู้ ทำงานเบื้องหลังอย่างไม่เห็นแก่ตัว พวกเขามักจะพบกับความไม่จริงใจ การหลอกลวง และความขัดแย้งในชีวิต");
            predictions.add("บุคคลที่มีความมั่นใจในตนเองสูง มีความทะเยอทะยานสูง ชอบความแปลกใหม่ และไม่ชอบความสอดคล้อง พวกเขาให้ความสำคัญกับเกียรติของตนเอง และไม่ชอบอยู่ใต้อำนาจของใคร กล้าหาญและกล้าหาญ พวกเขามักจะขาดความอ่อนโยน ชีวิตมักมีอุปสรรค และพวกเขา มักเผชิญกับการแข่งขันหรือศัตรูในการแสวงหา บางครั้งพวกเขาตกเป็นเป้าหมายของผู้ประสงค์ร้าย และผู้ใต้บังคับบัญชาหรือผู้ร่วมงานอาจสร้างปัญหาให้กับพวกเขา");
            predictions.add("คนเอาแต่ใจตัวเอง ชอบโอ้อวด ชอบลำเอียงในการกระทำ มักชอบใจตนเอง เป็นคนดื้อรั้น จิตใจไม่สงบ เชื่อใจผู้อื่นได้ง่าย อย่างไรก็ตาม ควรระมัดระวังให้ดีเท่าที่ควร ถูกทรยศหรือหักหลังโดยคนที่พวกเขาไว้วางใจ");
            predictions.add("หยิ่งยโส ถูกครอบงำได้ง่าย และมักถูกฟุ้งซ่านด้วยกิเลสตัณหาชั่วขณะ มักจะลืมความเจ็บปวดในอดีต และมักเชื่อใจผู้อื่นง่ายเกินไป ส่งผลให้ถูกบงการ ชีวิตอาจนำมาซึ่งอุบัติเหตุบ่อยครั้ง และปัญหาสุขภาพเรื้อรังอาจเกิดขึ้นได้ ความท้าทายที่ไม่หยุดยั้ง เมื่อพวกเขาผูกพันกับใครสักคน พวกเขามักจะทุ่มเทอย่างเต็มที่ โดยละสายตาจากทุกสิ่งทุกอย่าง");
            predictions.add("ชีวิตมักพลิกผันทั้งโชคลาภและโชคลาภอย่างกะทันหัน โชคลาภอาจเกิดขึ้นได้ แต่ก็สมกับโชคร้ายที่คาดไม่ถึง ไม่ชอบถูกท้าทายหรือเดินตามรอยเท้าผู้อื่น มีนิสัยกล้าหาญ ไม่เกรงกลัว อย่างไรก็ตาม ซึ่งบางครั้งอาจนำไปสู่อันตรายและความล้มเหลวในที่สุด");
            predictions.add("มีความรู้และความสามารถเป็นเลิศ มีสติปัญญาและความคิดที่รวดเร็ว มีตำแหน่งที่โดดเด่นในอาชีพการงานและได้รับความเคารพนับถือจากผู้อื่น พวกเขายึดถือค่านิยมทางศีลธรรมที่เข้มแข็งและสำนึกในความยุติธรรมอย่างลึกซึ้ง ในขณะที่อาจมี มีอุปสรรคเล็กน้อยระหว่างทาง สิ่งเหล่านี้ถือเป็นความท้าทายตามปกติ โดยรวมแล้ว พวกเขาประสบกับความสำเร็จที่สำคัญและความก้าวหน้าอย่างต่อเนื่อง");
            predictions.add("เลขมงคล มีมิตรสหายและคนที่รักมากมาย นิสัยดี จิตใจดี เข้ากับทุกคนได้ง่าย มีศิลปะการพูดเป็นที่รักของผู้อื่นและให้กำลังใจ โชคชะตาของพวกเขาคือ ย่อมมีเกียรติยศ ชื่อเสียง และความโชคดี");
            predictions.add("พวกเขาเผชิญความยากลำบาก อุปสรรค และโชคร้ายมากมาย มักพบกับโชคร้ายมากกว่าความสำเร็จ แม้จะมีชะตาลิขิตสูง เกิดมาในความมั่งคั่งและสิทธิพิเศษ แต่ชีวิตก็ยังนำความท้าทายมาให้ พวกเขามีศัตรูมากมายและอาจต้องทนทุกข์จากการต่อต้าน . แม้ว่าพวกเขาจะประสบความสำเร็จอย่างมาก แต่ก็ควรระมัดระวัง เนื่องจากอาจนำไปสู่ความหายนะที่ไม่คาดคิดและโชคร้ายได้");
            predictions.add("พวกเขาฉลาดมากและเข้าใจสิ่งต่าง ๆ ได้อย่างรวดเร็ว แต่ชีวิตของพวกเขามักจะเต็มไปด้วยอุปสรรคและความยากลำบาก พวกเขามักจะเผชิญกับแรงกดดันจากผู้มีอำนาจซึ่งอาจนำมาซึ่งอันตรายได้เช่นกัน ชีวิตของพวกเขาเต็มไปด้วยขึ้น ๆ ลง ๆ ความไม่แน่นอนและไม่มั่นคง ความทะเยอทะยานของพวกเขามักจะไม่บรรลุผล พวกเขามักจะถูกชักจูงจากผู้อื่น มีแนวโน้มที่จะไว้วางใจได้ง่ายเกินไป และมีลักษณะดื้อรั้นและเอาแต่ใจตนเอง");
            predictions.add("พวกเธอมีนิสัยกระสับกระส่าย เคลื่อนไหวบ่อย หรือประสบกับความเปลี่ยนแปลง มักจะไม่พอใจเรื่องความสัมพันธ์และการแต่งงาน ตัวเลขนี้ไม่เหมาะกับผู้หญิง เพราะเป็นการเตือนเรื่องการทรยศ การหลอกลวง และการหลงผิด");
            predictions.add("ตัวเลขนี้โดยทั่วไปแล้วเป็นมงคล นำความสำเร็จและความโดดเด่นในด้านการศึกษาและอาชีพ เป็นหลักประกันความเจริญรุ่งเรืองและหลีกเลี่ยงโชคร้าย อย่างไรก็ตาม เหมาะกับผู้ชายมากกว่าผู้หญิง สำหรับผู้หญิง อาจนำมาซึ่งความท้าทายทั้งการกลั่นแกล้งและการไม่สมหวัง ความรัก ในขณะที่ผู้ชายมักจะประสบกับความสำเร็จและเกียรติยศอันยิ่งใหญ่");
            predictions.add("พวกเขาไม่ชอบอยู่นิ่งๆ และมีความทะเยอทะยานอันแรงกล้า มักฝันกลางวัน ตัวเลขนี้ไม่เหมาะกับผู้หญิงเพราะมันนำมาซึ่งความท้าทายในความสัมพันธ์ พวกเขาอาจเผชิญกับแรงกดดันที่นำไปสู่การเป็นโสดหรือเป็นเมียน้อยที่อาศัยอยู่ภายใต้ การควบคุมผู้อื่น");
            predictions.add("พวกเขาเป็นบุคคลที่มีเสน่ห์และมีความตั้งใจอันแรงกล้า มักจะตัดสินใจในสิ่งที่คนอื่นไม่คาดคิด อย่างไรก็ตาม ชีวิตของพวกเขามักจะขาดความสมหวัง และพวกเขาต่อสู้ดิ้นรนเพื่อค้นหาความมั่นคงหรือความแน่นอน");
            predictions.add("พวกเขาเป็นคนช่างฝัน อ่อนไหวทางอารมณ์ และถูกชักจูงได้ง่าย มักจะเชื่อใจผู้อื่นเร็วเกินไป ซึ่งนำไปสู่การถูกหลอกซ้ำแล้วซ้ำเล่า");
            predictions.add("พวกเขามีนิสัยเข้มแข็ง ก้าวร้าว มักโกรธง่าย ไม่ยอมพ่ายแพ้ต่อผู้อื่น มักจะเจ้าชู้ และหากเป็นผู้ชายก็อาจเจอเรื่องอื้อฉาวเกี่ยวกับเรื่องรัก ๆ ใคร่ ๆ ได้ อย่างไรก็ตาม ในฐานะผู้หญิง เธอมีแนวโน้มที่จะได้รับการสนับสนุนและคำแนะนำจากผู้มีอิทธิพล นำไปสู่ชีวิตที่ประสบความสำเร็จและเจริญรุ่งเรือง");
            predictions.add("ชีวิตที่อุดมสมบูรณ์ สบายๆ ปราศจากความยุ่งยาก ฉลาด ไหวพริบ ถ่อมตัว มีเพื่อนฝูงมากมาย เป็นคนใจดี มีความเห็นอกเห็นใจที่พร้อมจะช่วยเหลือผู้อื่นเสมอ หากมุ่งไปสู่การเป็น ดารา นักร้อง หรือศิลปิน พวกเขาจะประสบความสำเร็จและมีชื่อเสียงอย่างมาก อย่างไรก็ตาม พวกเขาไม่ควรใช้ชีวิตโดยประมาท การทำความดีและการกุศลจะช่วยเพิ่มโชคลาภและโชคชะตาอย่างมาก");
            predictions.add("ชีวิตเต็มไปด้วยอุปสรรคมากมาย และความสำเร็จจะเกิดขึ้นได้ก็ต่อเมื่อเอาชนะความท้าทายต่างๆ มากมาย พวกเขาอาจเผชิญศัตรู ถูกรังแก ถูกคุกคามจากอำนาจมืด ถูกปล้น หรือแม้กระทั่งถูกทำร้าย ชีวิตของพวกเขาจะถูกทำเครื่องหมายด้วยความผันผวนที่รุนแรง และ พวกเขาอาจเผชิญกับอันตรายถึงชีวิตได้ การเปลี่ยนแปลงอย่างต่อเนื่องทำให้ชีวิตของพวกเขาไม่มั่นคง ไม่มีความมั่นคงหรือความสงบสุขที่ยั่งยืน");
        } else {
            predictions.add("A star that is powerful and mighty, possessing courage and wit, with excellent ideas and renowned leadership qualities. Determined, ambitious, and adventurous, it loves to explore everywhere and enjoys new experiences. It is highly respected by many.");
            predictions.add("A dreamer with an artistic temperament, optimistic, and skilled at problem-solving. A caring and compassionate individual who is thoughtful towards others.");
            predictions.add("Loves freedom but can lack focus, enjoys seeking knowledge, and aspires to high levels of education. Speaks bluntly and values justice, with a generous and open-minded nature. Should be cautious about health issues related to the nervous system and avoid overworking.");
            predictions.add("An intelligent and quick-witted individual who is perceptive and understands people well. Skilled at presenting themselves appropriately in any situation, they enjoy long journeys. At times, they may feel a lack of true friends, but their intelligence leads them to achieve success in life.");
            predictions.add("A knowledgeable and wise individual who is trustworthy and commands respect and admiration from others. Their work typically proceeds smoothly, guided by strong morals, kindness, and compassion, with an inclination towards spirituality and righteousness.");
            predictions.add("Lives a comfortable and joyful life, full of imagination and a love for art and freedom. Prefers personal space, never harming others, and possesses a kind and compassionate heart. Loved by many, charming, and holds a respectable position with wealth and prosperity.");
            predictions.add("A freedom-loving individual with a strong sense of individuality, uninterested in following others. Passionate about the arts—writing, drawing, or poetry—and possesses a unique talent for problem-solving. Generous and charitable by nature.");
            predictions.add("A quiet and shy individual who prefers solitude and does not easily socialize with others. As a child, they may have struggled with health issues but grow stronger with age and live a long life. They have a subtle sense of humor, though they rarely display it, often appearing serious. They tend to let others' opinions override their own ideas.");
            predictions.add("Appears to be arrogant but deeply lacks self-confidence. They are earnest and determined but often make impulsive decisions, later regretting them. Honest and straightforward, they have a quick temper but forgive just as quickly. Despite their efforts to help others, their contributions often go unrecognized, working selflessly behind the scenes. They frequently encounter insincerity, deceit, and contradictions in life.");
            predictions.add("A highly self-confident and ambitious individual who enjoys novelty and dislikes conformity. They value their honor and prefer not to be under anyone's authority. Bold and daring, they tend to lack gentleness. Life often presents obstacles, and they frequently face competition or adversaries in their pursuits. They are sometimes targeted by ill-wishers, and subordinates or associates may cause trouble for them.");
            predictions.add("A self-centered individual who enjoys boasting and tends to be biased in their actions, often favoring themselves. They have a stubborn nature and a restless mind, easily trusting others. However, they should be cautious, as they may be betrayed or backstabbed by those they trust.");
            predictions.add("Arrogant, easily influenced, and prone to being distracted by fleeting desires. They tend to forget past hurts and often trust others too easily, making them vulnerable to manipulation. Life may bring frequent accidents, and chronic health issues can be a persistent challenge. Once they become attached to someone, they tend to be completely devoted, losing sight of everything else.");
            predictions.add("Life often presents sudden turns of both good and bad fortune. Unexpected luck may arise, but it is equally matched by unforeseen misfortunes. They dislike being challenged or following in others' footsteps, possessing a bold, fearless nature. However, this can sometimes lead to danger and eventual failure in the end.");
            predictions.add("Possesses great knowledge and ability, with intelligence and quick thinking. They hold a prominent position in their career and are well-respected by others. They uphold strong moral values and a deep sense of justice. While there may be a few obstacles along the way, these are normal challenges. Overall, they experience significant success and continuous progress.");
            predictions.add("A fortunate number with many friends and loved ones. They have a good temperament and a kind heart, easily getting along with everyone. They possess the art of speaking, which endears them to others and brings support. Their destiny is marked by honor, fame, and good fortune.");
            predictions.add("They face many hardships, obstacles, and bad luck, often encountering misfortune rather than success. Despite having high destiny, being born into wealth and privilege, life still brings challenges. They have many enemies and may suffer from their opposition. Even if they achieve great success, they should be cautious, as it may lead to an unexpected and unfortunate downfall.");
            predictions.add("They are highly intelligent and quick to understand things, but their life is often filled with obstacles and difficulties. They frequently face pressure from authority figures, who may also bring harm. Their life tends to be full of ups and downs, uncertain and unstable. Their ambitions often go unfulfilled. They are easily influenced by others, tend to trust too easily, and have a stubborn, self-centered nature.");
            predictions.add("They have a restless nature, frequently moving or experiencing changes. They are often dissatisfied with matters of relationships and marriage. This number is not suitable for women, as it brings caution regarding betrayal, deceit, and being misled.");
            predictions.add("This number is generally favorable, bringing success and distinction in education and career. It ensures prosperity and avoids misfortune. However, it is better suited for men than women. For women, it may bring challenges, including bullying and unfulfilled love, while men are likely to experience great achievements and glory.");
            predictions.add("They dislike staying still and have strong ambitions, often daydreaming. This number is not suitable for women, as it brings challenges in relationships. They may face pressure that leads to either being single or becoming a mistress, living under the control of others.");
            predictions.add("They are a person with charm and a strong will, often making decisions that others wouldn't expect. However, their life tends to lack fulfillment, and they struggle to find stability or certainty.");
            predictions.add("They are dreamers, emotionally sensitive, and easily influenced, often trusting others too quickly, which leads to being deceived repeatedly.");
            predictions.add("They have a strong, aggressive temperament and are often quick to anger, refusing to be defeated by others. They tend to be flirtatious, and if male, may face scandals related to love affairs. As a woman, however, she is likely to receive support and guidance from influential figures, leading to a successful and prosperous life.");
            predictions.add("A life of abundance and ease, free from complications. They are intelligent, quick-witted, and humble, with many friends. A kind and compassionate person who is always ready to help others. If they focus on becoming a star, singer, or artist, they will achieve great success and fame. However, they should not take life for granted; making good deeds and charity will greatly enhance their fortune and destiny.");
            predictions.add("Life is filled with many obstacles, and success comes only after overcoming numerous challenges. They may face enemies, be bullied, threatened by dark forces, robbed, or even assaulted. Their life will be marked by intense fluctuations, and they could face life-threatening harm. Constant change makes their life unstable, with no certainty or lasting peace.");
        }
        return predictions;
    }

    public void showDialog() {
        if( (Math.abs(sensor_info.accX)>shake_threshold) || (Math.abs(sensor_info.accY)>shake_threshold) || (Math.abs(sensor_info.accZ)>shake_threshold) ) {
            if(!shown_dialog) {
                shown_dialog = true;
                int randomInt = random.nextInt(100);
                ArrayList<String> predictions = getPredictions();
                final AlertDialog.Builder viewDialog = new AlertDialog.Builder(this);
                viewDialog.setIcon(android.R.drawable.dialog_frame);
                viewDialog.setTitle(R.string.dialogtitle);

                int predictionIndex = randomInt % predictions.size();
                String currentLanguage = Locale.getDefault().getLanguage();
                String message = "\n" + getString(R.string.predictions) + ": " + predictions.get(predictionIndex);
                final TextView predictions_msg = findViewById(R.id.predictions);
                predictions_msg.setText(String.valueOf(message));

                viewDialog.setMessage(String.valueOf(randomInt));

                final TextView luckynumber = findViewById(R.id.luckynumber);
                luckynumber.setText(String.valueOf(randomInt));

                viewDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                shown_dialog = false;
                            }
                        });
                viewDialog.show();
            }//end if
        }//end if
    }

    @SuppressLint("WakelockTimeout")
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        hdr.postDelayed(pollTask, POLL_INTERVAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        hdr.removeCallbacks(pollTask);
    }

    static class SensorInfo{
        float accX, accY, accZ;
    }

}