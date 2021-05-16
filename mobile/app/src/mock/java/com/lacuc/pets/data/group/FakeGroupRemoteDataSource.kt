package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.util.*
import javax.inject.Inject

class FakeGroupRemoteDataSource @Inject constructor() : GroupDataSource {

    private var groupData = mutableListOf<Group>()
    private var memberData = mutableListOf<Member>()
    private var imageData = mutableListOf<GroupImage>()
    private var itemData = mutableListOf<ItemHistory>()
    private var profile = Member("0", "foo", "foo", "foo", "foo")

    init {
        initGroup()
        initMember()
        initImage()
        initItem()
    }

    override suspend fun getMyGroups(): Result<List<GroupWrapper>> = withContext(Dispatchers.IO) {
        delay(100)
        Result.Success(groupData.map { GroupWrapper(it) })
    }

    override suspend fun deleteGroup(gid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroup(
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        val group = Group(
            UUID.randomUUID().toString(),
            params.getValue("name"),
            params.getValue("info"),
            "",
            params.getValue("share").toBoolean()
        )
        groupData.add(group)
        Result.Success(null)
    }

    override suspend fun getGroup(gid: String): Result<Group> = withContext(Dispatchers.IO) {
        Result.Success(groupData.find { it.GID == gid })
    }

    override suspend fun updateGroup(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        val group = groupData.find { it.GID == gid }
        group?.let {
            groupData = groupData.filter { it.GID != gid } as MutableList<Group>
            groupData.add(
                Group(
                    group.GID,
                    params["name"]!!,
                    params["info"]!!,
                    group.image,
                    params["share"].toBoolean()
                )
            )
        }
        Result.Success(null)
    }

    override suspend fun addGroupMember(
        gid: String,
        email: String,
        authority: String
    ): Result<Void> = withContext(Dispatchers.IO) {
        val target = memberData.find { it.email == email }
        target?.let {
            memberData.add(it)
        }
        Result.Success(null)
    }

    override suspend fun getGroupMembers(gid: String): Result<List<Member>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(memberData)
        }

    override suspend fun getGroupMember(gid: String, uid: String): Result<Member> =
        withContext(Dispatchers.IO) {
            Result.Success(memberData.find { it.UID == uid })
        }

    override suspend fun getProfile(): Result<Member> = withContext(Dispatchers.IO) {
        Result.Success(profile)
    }

    override suspend fun updateProfile(
        name: String,
        email: String,
        nickName: String
    ): Result<Void> = withContext(Dispatchers.IO) {
        profile = Member(profile.UID, name, profile.password, email, nickName)
        Result.Success(null)
    }

    override suspend fun deleteGroupMember(gid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(gid: String): Result<List<GroupImage>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(imageData)
        }

    override suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage> =
        withContext(Dispatchers.IO) {
            Result.Success(imageData.find { it.iid == iid })
        }

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void> =
        withContext(Dispatchers.IO) {
            imageData.add(
                GroupImage(
                    UUID.randomUUID().toString(),
                    "0",
                    imageParams["url"] as String,
                    imageParams["tag"] as String
                )
            )
            Result.Success(null)
        }

    override suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void> =
        withContext(Dispatchers.IO) {
            val index = imageData.indexOf(imageData.find { it.iid == imageParams["IID"] })
            imageData[index] = GroupImage(
                imageParams["IID"] as String,
                imageParams["GID"] as String,
                imageParams["url"] as String,
                imageParams["tag"] as String,
            )
            Result.Success(null)
        }

    override suspend fun getItems(gid: String): Result<List<ItemHistory>> =
        withContext(Dispatchers.IO) {
            Result.Success(itemData.filter { it.gid == gid })
        }

    override suspend fun setItem(itemHistory: ItemHistory): Result<Void> =
        withContext(Dispatchers.IO) {
            itemData.add(itemHistory)
            Result.Success(null)
        }

    override suspend fun updateItem(iid: String, itemHistory: ItemHistory): Result<Void> =
        withContext(Dispatchers.IO) {
            itemData = (itemData.filter { it.hid != iid } + itemHistory) as MutableList<ItemHistory>
            Result.Success(null)
        }

    override suspend fun deleteItem(iid: String): Result<Void> = withContext(Dispatchers.IO) {
        itemData = itemData.filter { it.hid != iid } as MutableList<ItemHistory>
        Result.Success(null)
    }

    private fun initGroup() {
        groupData.add(
            Group(
                "0",
                "Group1",
                "그룹 1 소개",
                "https://images.unsplash.com/photo-1593643946890-b5b85ade6451?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2129&q=80",
                false
            )
        )
        groupData.add(
            Group(
                "1",
                "Group2",
                "aco wqp awji  snl cnqwo c aslc wiz xlc  wia cwalwcia sic raltj wj cor fosajrw sa w skaj",
                "https://images.unsplash.com/photo-1617289749213-c2a7b44f6523?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1914&q=80",
                false
            )
        )
        groupData.add(
            Group(
                "2",
                "Group3",
                "info \n info \n info",
                "https://images.unsplash.com/photo-1617286856618-f9411fcc1800?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80",
                false
            )
        )
    }

    private fun initMember() {
        memberData.add(Member("0", "Linux", "admin", "mail", "L"))
        memberData.add(Member("1", "Ubuntu", "admin", "mail", "U"))
        memberData.add(Member("2", "Arch", "admin", "mail", "A"))
    }

    private fun initImage() {
        imageData.add(
            GroupImage(
                "0",
                "0",
                "https://images.unsplash.com/photo-1529933037705-0d537317ae7b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1271&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "1",
                "0",
                "https://images.unsplash.com/photo-1517451330947-7809dead78d5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "2",
                "0",
                "https://images.unsplash.com/photo-1478958813546-b8bd142c3202?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1332&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "3",
                "0",
                "https://images.unsplash.com/photo-1609859159443-5dda6401ebdf?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1164&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "4",
                "0",
                "https://images.unsplash.com/photo-1572360620839-a02312482daf?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1234&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "5",
                "0",
                "https://images.unsplash.com/photo-1568307970720-a8c50b644a7c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                "cat"
            )
        )
        imageData.add(
            GroupImage(
                "6",
                "0",
                "https://images.unsplash.com/photo-1612607323373-4614b03146b4?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1233&q=80",
                "cat"
            )
        )
    }

    private fun initItem() {
        itemData.add(
            ItemHistory(
                "0",
                "0",
                "로얄캐닌 고양이 사료",
                "고양이 사료",
                "https://search.shopping.naver.com/bridge/searchGate.nhn?query=%EA%B3%A0%EC%96%91%EC%9D%B4+%EC%82%AC%EB%A3%8C&bt=1&nv_mid=81195838622&cat_id=50006679&h=3e50f203941275637c9a154b7a99017ea80f7e4f&t=KOH3HF7Y&frm=NVSCPRO",
                32900
            )
        )
        itemData.add(
            ItemHistory(
                "1",
                "0",
                "패스룸 프리미엄 고양이 모래",
                "고양이 모래",
                "https://adcr.naver.com/adcr?x=IvaaG2kmday/4Rj+egxDjP///w==kHXPiEPwCJFa7+9ThDe9+Jcnwyhf7ADYhh1M2Jmn3zOQtSuIfNYRhuNlGwGIYe43Wi+uwWhsme077spYkW+jUitj2ir5Ef4emcqB7Q7eWkH2ni1aO3umMjWZ2ZObUjLgAtlDvhwK56RuxOBay4xpr/3b3n8Sh/XAZq4n0wqfp41dcW3QEBuAoicY8vlzgkaN+r67MX7nxNupMgZ/mVg6OF6prhU44fVbJZfnoaTK+nlg8EZUbIv6uqjHDrhiVNR154mfTd3a5y82D/qkeSgJ3k4tUzWtX62kmRQccJRknQQQTSH8tD9OUyFgp4yLZElLECpoqhU4ZV/reXuV2TYWREohr9Ct3lZFabY5l8EBzXWMjTlIIukO14rbFpNDhCHTRtyeAeDo7KTYZ8G2ff91m6IBsEeTI4IfChJU06J4ICSdDlYA1G/CorAMTFsVqG6J4DLATsqn5IxiI4JHlav8v3eK+COwrT65snYHLC/S7+QRtsc9ItgSvtG//MmEFNmH+4lKFi5s05AriamTVOHTqsyU2KgmpB7b2Y1aWZzqSyoDLwJN96StQ4ohn3ej0rogNyHbr4a80PA4wnk0PNUqCe97zzOceg7eMiXDukKCNu90CIEFIIOYgU7JSM6qLvvEah4BeCy1ImmkhNrIngiVPbJxsjbSK0BxreLQ9thQqKNF0/r7dXaO2zZFcWO8H9dmk84PPOxL6TrKZxzFtotP3G75Q0Jv9+laok1p5unprVfTznqbMWHXb0gm5lR1oYviXYNgpt7RjQvsJviXQl1Fw3o85WomtgCcpdDPfJfuJg6vOdpyJKCZeZEuMow6Gkk4FMMwnnCFIeIQfz2Es7lpGZrjMUtOvI/Vj5LjuPQvekzV0QgdzrpmYKhf6XdQDaA+61srXpPBlpxOQ5I2EgXnLrM4WFrNrabyLomKTLdHM9hofh3k72BUZ3xDFTBzBVkUXS0OXT5x9jo5M24Mwfafu6A==&ui=GUIDE",
                22900
            )
        )
        itemData.add(
            ItemHistory(
                "2",
                "0",
                "캐티맨 펫모닝 강아지풀",
                "고양이 장난감",
                "https://adcr.naver.com/adcr?x=vbUVPUrVxH8Uavd2JrbbZ////w==kihy2QORdPOg8sWPW7satPeV6Zy1Vn+uixjmCITuVQjV0/CjQL9Lpd5ejx5TWkLzJLIFfCg+SK4AMYr6/3R7vAyY2huByXYEvzsMN0qTtBtGlX88WZKKES2UUPFWS+Mk0NPEfXGZCGavOmix4zsLIdZM7hg6nc65oun3jqqlMSt0Ib2wLVn1+bnMAgQH9uosZsf9sPBDkv3aUyyjhQa9u5V82WkW+i4ow83el4APOflOeNrsMztfKHHipGru9HV+1bkarJLvAxcY0jhorPmOi8sGmNff6SuXZitIrMWqPPcYIe9z6fHvowzOVNXOw5h8kRrwKWpMxISs88+nNzh/HTA82vIL/Lv5SVqueOney/sq5V1LCn9OKo2R7LtnVGL9Mbl6M697P4XCGSBF76tSLuFQ2Wr1sRlRQRZGLWRCAkdO8kmaqWM4j2MEWXTPn0LaBIx3m8J3/wngR+WwBIesLIgSH9v1ldpSEf9XgDAHyqhpyTd4VIVhPhHsmUYfsETw5JJ59tOjT2ZtJp0n+Vqtq6GrrAnBCsi/6d/raWbU4bz5EekWhmU8WESx2Xclj8BZRdrdJBJDMl5evFJsr4QdqjW6WVq07v8w05GKvvA6hOAYtIa3qNW7i6GOthh6xUGblcK2/5Rax2S5l1w6wuzyqM+ZN5TlDy2Dy7gwv1LGerAvA9kEGywBw/KjINssn9s7jCPSYVyWM/8vYsj2ggv1Z8F7Z7vPDKwibFqaEYwP9qo1qYMZ1XbynKMCvCuHF2iCR3zT2j78bOQ+XICDZxJ7sMhzQbMjfG2nwZfK1Xx9naIaoPhDIOIi2UJK6690PeSgd53Up9rL6zy5yB09ifJl5LA==&ui=GUIDE",
                1990
            )
        )
        itemData.add(
            ItemHistory(
                "3",
                "0",
                "이나바 챠오 츄르",
                "고양이 간식",
                "https://search.shopping.naver.com/bridge/searchGate.nhn?query=%EA%B3%A0%EC%96%91%EC%9D%B4+%EC%B8%84%EB%A5%B4&bt=1&nv_mid=10063637668&cat_id=50006685&h=0c2cf9c58c35492c9d3965988feba5a06cb98f93&t=KOH3LT8Y&frm=NVSCPRO",
                1920
            )
        )
    }

}