<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" class="w-100 border-0" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				<div class="d-flex">
					<input type="file" id="file" class="d-none" accept=".jpg,.jpeg,.gif,.png">
					<a href="#" id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/512/44/44289.png" width="35" class="m-1">
					</a>
					
					<!-- 업로드 된 이미지 파일명을 띄움 -->
					<div id="fileName"></div>
				</div>
				<button type="button" class="btn btn-info m-1">게시</button>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	// 파일업로드 이미지 클릭 => input type="file" 숨어있던 창이 열림
	$('#fileUploadBtn').on('click',function(e){
		e.preventDefault();		// a 태그를 클릭했을 때 창이 위로 올라가는 것을 방지
		$('#file').click();		// input file을 클릭한 것과 같은 효과
	});
	
	// 파일 업로드를 했을 때, 파일 이름 노출, 파일 확장자 검증
	$('#file').on('change',function(e){		// 태그의 변화 감지 (이미지가 선택됐을 때)
		let fileName = e.target.files[0].name;	// ex) image.jpg
		let arr = fileName.split(".");
		
		// 확장자 검증
		if(arr.length < 2 || 
				(arr[arr.length-1] != 'gif'
						&& arr[arr.length-1] != 'jpg'
						&& arr[arr.length-1] != 'jpeg'
						&& arr[arr.length-1] != 'png')){
			alert("이미지 파일만 업로드 가능합니다.");
			$(this).val("");			// 서버에 잘못된 파일이 올라가기 전에 파일을 비움
			$('#fileName').text("");	// 파일 이름 뜨는 것도 지워줌
			return;
		}
		
		// 임시파일명 노출
		$('#fileName').text(fileName);
	});
});
</script>