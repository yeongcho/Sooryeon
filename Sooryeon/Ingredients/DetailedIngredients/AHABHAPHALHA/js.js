  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  document.getElementById('changeContentButton1').addEventListener('click', function() {
    document.getElementById('titleContent').innerHTML = '01. AHA';
    document.getElementById('function').innerHTML = '- 보습작용, 피부 연화작용, 각질제거<br>- 자극 있음 ';
    document.getElementById('content1').innerHTML = 'α의 위치에 수산화기를 가지고 있어서 붙여진 이름으로 천연적으로 존재하는 복합체이며 산성의 휘발성물질이다.';
    document.getElementById('content2').innerHTML = '대표적으로 사탕수수에서 추출하는 글리콜릭 산, 발효우유의 젖산, 포도의 주석산, 사과의 말릭산, 구연산, 쌀·곡류의 피틱산 등이 있다.';
    document.getElementById('thesis').innerHTML = '이자복, AHA와 MHA의 여드름 피부 개선 효과.<br>건국대학교 산업대학원(2009)';
    document.getElementById("thesisLink").setAttribute("href", "https://www.riss.kr/search/detail/DetailView.do?p_mat_type=be54d9b8bc7cdb09&control_no=0f02adfe14730d10ffe0bdc3ef48d419");
  });

  document.getElementById('changeContentButton2').addEventListener('click', function() {
    document.getElementById('titleContent').innerHTML = '02. BHA';
    document.getElementById('function').innerHTML = '- 각질 제거, 면포 용해작용 <br> - 자극 있음';
    document.getElementById('content1').innerHTML = '우수한 안정성과 특이적인 면포 용해 작용이 있어, 광노화, 기미, 여드름, 염증 반응 후 색소 침착, 거친 피부, 기름진 피부 등의 질환에 유용하게 사용될 수 있다.';
    document.getElementById('content2').innerHTML = '대표적으로 살리실릭애씨드가 있다.';
    document.getElementById('thesis').innerHTML = '이호섭 & 김일환, Beta Hydroxy acid 가 여드름 환자의 얼굴 피부에 미치는 영향.<br>대한피부과학회지(2002)';
    document.getElementById("thesisLink").setAttribute("href", "https://lib.swu.ac.kr/#/eds/detail?an=edskis.1952871&dbId=edskis");
  });

  document.getElementById('changeContentButton3').addEventListener('click', function() {
    document.getElementById('titleContent').innerHTML = '03. PHA';
    document.getElementById('function').innerHTML = '- 각질 제거, 보습작용, 주름 감소, 안색 개선 <br>- 자극 적음';
    document.getElementById('content1').innerHTML = 'AHA와 달리 분자구조가 커서 피부에 천천히 흡수되기 때문에 상대적으로 자극이 덜하다. ';
    document.getElementById('content2').innerHTML = '각질 제거와 피붓결 관리에 효과가 탁월한 글루코노락톤과 각질층과 노폐물 용해 효과가 있는 락토바이오산으로 구성되어 있다.';
    document.getElementById('thesis').innerHTML = '김여진, 아하, 바하, 지금은 파하(PHA)!<br>VOGUE(2023)';
    document.getElementById("thesisLink").setAttribute("href", "https://www.vogue.co.kr/2019/07/12/%EC%95%84%ED%95%98-%EB%B0%94%ED%95%98-%EC%A7%80%EA%B8%88%EC%9D%80-%ED%8C%8C%ED%95%98pha/?utm_source=naver&utm_medium=partnership");
  });

  document.getElementById('changeContentButton4').addEventListener('click', function() {
    document.getElementById('titleContent').innerHTML = '04. LHA';
    document.getElementById('function').innerHTML = '- 각질 제거, 피지 조절 <br> - 자극 적음';
    document.getElementById('content1').innerHTML = '피부장벽을 해치지 않는 가장 큰 분자 크기를 가진 성분으로 서서히 침투해 모공 노폐물을 제거해준다.';
    document.getElementById('content2').innerHTML = 'BHA의 유도체이자 약산성 물질이다.';
    document.getElementById('thesis').innerHTML = '최주현, 건조하고 푸석한 피부… 피부장벽 강화하려면?<br>여성조선(2021)';
    document.getElementById("thesisLink").setAttribute("href", "https://woman.chosun.com/news/articleView.html?idxno=92580");
  });