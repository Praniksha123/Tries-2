//problem1
class Solution {
    List<List<String>> result;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        this.result = new ArrayList<>();
        this.root = new TrieNode();

        for (String word : words) {
            insert(word);
        }

        List<String> path = new ArrayList<>();
        for (String word : words) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }

        return result;
    }

    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }

        return curr.startsWith;
    }

    private void dfs(String[] words, List<String> path) {
        if (path.size() == words[0].length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        int idx = path.size();
        StringBuilder prefix = new StringBuilder();
        for (String str : path) {
            prefix.append(str.charAt(idx));
        }

        for (String word : search(prefix.toString())) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }
    }
}
//problem2
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n=queries.length;
          List<Boolean> res=new ArrayList<>();
        for(int i=0;i<n;i++){
            String ch=queries[i];
            int q=0,p=0;
            boolean found=true;
            while(q<ch.length()){
                if( p<pattern.length() &&ch.charAt(q)==pattern.charAt(p)){
                    q++;
                    p++;
                }else if(Character.isUpperCase(ch.charAt(q))){
                    found=false;
                    break;
                }else{
                    q++;
                }
            }
            if(p!=pattern.length()){
                found=false;
            }
            res.add(found);
        }
        return res;
    }
}
//problem3
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        HashMap<Integer,List<Integer>> bucket=new HashMap<>();
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int x:map.keySet()){
            int y=map.get(x);
            if(!bucket.containsKey(y)){
                bucket.put(y,new ArrayList<>());
            }
            bucket.get(y).add(x);
            max=Math.max(max,y);
            min=Math.min(min,y);
        }
        int[] res=new int[k];
        int j=0;
        for(int i=max;i>=min && j<k;i--){
            if(bucket.containsKey(i)){
                for(int sp:bucket.get(i)){
                    res[j]=sp;
                    j++;
                    if(j==k){
                        break;
                    }
                }
            }
        }
        return res;
    }
}
